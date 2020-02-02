package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/sportsman")
public class SportsmanController {
    private SportsmanService service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SportsmanController(SportsmanService service){
        this.service = service;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSportsman(@Valid @RequestBody SportsmanDTO sportsman){
        logger.info("Attempting to add new sportsman.");
        service.addSportsman(sportsman);
    }

    @GetMapping("/{pesel}/dateandtime")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats(@PathVariable("pesel") CharSequence sportsmanPesel){
        logger.info("Attempting to get sportsman date and time statistics.");
        return new ResponseEntity<>(service.getSportsmanStatistics(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/{pesel}/gettrainer")
    @ResponseStatus(HttpStatus.CREATED)
    public void chooseTrainer(@PathVariable("pesel") CharSequence sportsmanPesel, @RequestBody CharSequence trainerPesel){
        logger.info("Attempting to choose trainer.");
        service.chooseTrainer(sportsmanPesel, trainerPesel);
    }

    @GetMapping("/mytrainer")
    public ResponseEntity getMyTrainer(@RequestBody CharSequence sportsmanPesel){
        logger.info("Attempting to display sportsman's.");
        return new ResponseEntity<>(service.getMyTrainerData(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/trainCardio")
    @ResponseStatus(HttpStatus.OK)
    public void trainCardio(@RequestBody CharSequence sportsmanPesel){
        logger.info("Attempting to do cardio training.");
        service.trainCardio(sportsmanPesel);
    }

    @PutMapping("/trainPower")
    @ResponseStatus(HttpStatus.OK)
    public void trainPower(@RequestBody CharSequence sportsmanPesel){
        logger.info("Attempting to do power training.");
        service.trainPower(sportsmanPesel);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity handleExistingRecordInDatabase(){
        return new ResponseEntity<>("Sportsman with given pesel number already exists.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("Sportsman with given pesel does not exists.", HttpStatus.NOT_FOUND);
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
