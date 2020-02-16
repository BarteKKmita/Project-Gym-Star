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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTrainer(@RequestBody TrainerDTO trainer){
        LOGGER.info("Attempting to add new trainer to database. {}", this.getClass().getName());
        service.addTrainer(trainer);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainers(){
        LOGGER.info("Attempting to get all trainers to database. {}", this.getClass().getName());
        return new ResponseEntity<>(service.getAllTrainers(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleEmptyResult(){
        return new ResponseEntity<>("Entered not suitable trainer data", HttpStatus.BAD_REQUEST);
    }

}
