package com.learning.gym.star.training.cardio.controller;

import com.learning.gym.star.training.cardio.service.CardioTrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController()
@RequestMapping("/api/cardio")
public class CardioTrainingController {
    private CardioTrainingService service;

    public CardioTrainingController(CardioTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity getCardioTrainingCount(@PathVariable("id") int cardioId){
        return new ResponseEntity<>(service.getCardioTrainingCount(cardioId), HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void doCardioTraining(@PathVariable("id") int cardioId){
        service.doCardioTraining(cardioId);
    }

    @PutMapping("/reset/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void resetCardioStatistics(@PathVariable("id") int cardioId){
        service.resetCardioStatistics(cardioId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewCardioStatistics(){
        return new ResponseEntity<>("Your gym id nr : " + service.createNewCardioStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("There is no such cardio statistics in database.", HttpStatus.NOT_FOUND);
    }
}
