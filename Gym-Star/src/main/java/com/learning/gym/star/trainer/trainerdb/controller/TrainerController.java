package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrainerService service;

    public TrainerController(TrainerService service){
        this.service = service;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTrainer(@Valid @RequestBody TrainerDTO trainer){
        logger.info("Attempting to add new trainer to database. {}", this.getClass().getName());
        service.addTrainer(trainer);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainers(){
        logger.info("Attempting to get all trainers to database. {}", this.getClass().getName());
        return new ResponseEntity<>(service.getAllTrainers(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleEmptyResult(){
        return new ResponseEntity<>("Entered not suitable trainer data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
