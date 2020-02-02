package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.NoSuchElementException;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@RequestMapping("api/jpa/gym")
@RestController
public class GymControllerJpa {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GymServiceJpa gymService;

    public GymControllerJpa(GymServiceJpa gymService){
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity addGym(@Valid @NotNull @RequestBody GymDTO gymDTO){
        logger.info("Attempting to add gym to database. {}", this.getClass());
        String gymId = gymService.addGym(gymDTO);
        if (gymId.isEmpty()) {
            logger.error("Gym with given id: {} already exists. {}", gymDTO.getGymId(), this.getClass());
            return new ResponseEntity<>("Specified gym id already exists ", getResponseDateAndTime(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Your gym id: " + gymId, getResponseDateAndTime(), HttpStatus.CREATED);
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        logger.info("Attempting to get all available gyms. {}", this.getClass());
        return new ResponseEntity<>(gymService.getAllGyms(), getResponseDateAndTime(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        logger.info("Attempting to get gym by id. {}", this.getClass());
        GymDTO gymDTO = gymService.getGymById(gymId);
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-type", "application/json");
        header.addAll(getResponseDateAndTime());
        if (gymDTO != null) {
            return new ResponseEntity<>(gymDTO, header, HttpStatus.OK);
        } else {
            logger.error("Gym id passed by user does not exist. {}", this.getClass());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @RequestBody GymDTO gymDTO){
        logger.info("Attempting to update gym. {}", this.getClass());
        gymService.updateGym(gymDTO);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGymById(@PathVariable("id") String gymId){
        logger.info("Attempting to delete gym. {}", this.getClass());
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleWrongGymDTO(){
        return new ResponseEntity<>("Entered wrong gym data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleEmptyResult(){
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectUpdateSemanticsDataAccessException.class)
    public ResponseEntity handleWrongUpdateStatement(){
        return new ResponseEntity<>("Gym id cannot be null", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericJDBCException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity<>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }

    private MultiValueMap<String, String> getResponseDateAndTime(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("date", LocalDateTime.now().format(ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)));
        return headers;
    }
}