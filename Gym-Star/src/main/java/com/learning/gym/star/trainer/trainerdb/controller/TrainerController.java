package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private TrainerService service;

    public TrainerController(TrainerService service){
        this.service = service;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTrainer(@RequestBody TrainerDTO trainer){
        service.addTrainer(trainer);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTrainers(){
        return new ResponseEntity<>(service.getAllTrainers(), HttpStatus.OK);
    }
}
