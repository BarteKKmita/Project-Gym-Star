package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public final class TrainerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerController.class);
    private final TrainerService service;

    public TrainerController(TrainerService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTrainer(@RequestBody TrainerDTO trainer){
        LOGGER.info("Attempting to add new trainer to database.");
        service.addTrainer(trainer);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainers(){
        LOGGER.info("Attempting to get all trainers to database.");
        return new ResponseEntity<>(service.getAllTrainers(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleEmptyResult(){
        LOGGER.info("Entered not suitable trainer data. Serialization of TrainerDTO to TrainerEntity failure. Status 400 returned.");
        return new ResponseEntity<>("Entered not suitable trainer data", HttpStatus.BAD_REQUEST);
    }
}