package com.learning.gym.star.training.cardio.controller;

import com.learning.gym.star.training.cardio.service.CardioTrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cardio")
public final class CardioTrainingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardioTrainingController.class);
    private CardioTrainingService service;

    public CardioTrainingController(CardioTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity getCardioTrainingCount(@PathVariable("id") int cardioId){
        LOGGER.info("Attempting to get cardio training count");
        return new ResponseEntity<>(service.getCardioTrainingCount(String.valueOf(cardioId)), HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void doCardioTraining(@PathVariable("id") int cardioId){
        LOGGER.info("Attempting to increment cardio training count");
        service.doCardioTraining(String.valueOf(cardioId));
    }

    @PutMapping("/reset/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void resetCardioStatistics(@PathVariable("id") int cardioId){
        LOGGER.info("Attempting to reset cardio training count");
        service.resetCardioStatistics(String.valueOf(cardioId));
    }

    @PostMapping("/create")
    public ResponseEntity createNewCardioStatistics(){
        LOGGER.info("Attempting to create new cardio training");
        return new ResponseEntity<>("Your gym id nr : " + service.createNewCardioStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(NoSuchElementException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
