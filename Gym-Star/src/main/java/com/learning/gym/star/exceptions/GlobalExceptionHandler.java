package com.learning.gym.star.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.NoSuchElementException;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleAccessingNotExistingRecord(EmptyResultDataAccessException exception){
        LOGGER.info("Record not found. Status 404 returned. " + exception.getMessage());
        return new ResponseEntity<>("Record not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleDuplicatedId(SQLIntegrityConstraintViolationException exception){
        LOGGER.info("This id is occupied. Leave gym id empty and it will be autogenerated. Status 400 returned. " + exception.getMessage());
        return new ResponseEntity<>("This id is occupied. Leave gym id empty and it will be autogenerated.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity handleConnectionOutage(CannotCreateTransactionException exception){
        LOGGER.info("Connection with database cannot be established. Database maintenance required. Status 500 returned.");
        return new ResponseEntity<>("Connection with database cannot be established.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex){
        LOGGER.info("Validation error occurred. Status 400 returned.");
        var errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var parameter = ((FieldError) error).getField();
                    var message = error.getDefaultMessage();
                    LOGGER.info(parameter, message);
                    errors.put(parameter, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleValidationExceptions(ConstraintViolationException ex){
        LOGGER.info("Validation error occurred. Status 400 returned.");
        var errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(error -> {
                    var field = error.getInvalidValue();
                    var message = error.getMessage();
                    LOGGER.info(message);
                    errors.put(field, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(NoSuchElementException exception){
        LOGGER.info(exception.getMessage() + " Status 404 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
