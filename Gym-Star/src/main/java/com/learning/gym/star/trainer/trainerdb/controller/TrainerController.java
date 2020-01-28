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
public class TrainerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrainerService service;

    public TrainerController(TrainerService service){
        this.service = service;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTrainer(@RequestBody TrainerDTO trainer){
        logger.info("Attempting to add new trainer to database. {}", this.getClass().getName());
        service.addTrainer(trainer);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainers(){
        logger.info("Attempting to get all trainers to database. {}", this.getClass().getName());
        return new ResponseEntity<>(service.getAllTrainers(), HttpStatus.OK);
    }
}
