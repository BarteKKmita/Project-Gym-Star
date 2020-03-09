package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/power")
public class PowerTrainingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrainingController.class);
    private final PowerTrainingService service;

    public PowerTrainingController(PowerTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity getPowerTrainingCount(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to get power training count");
        return new ResponseEntity<>(service.getPowerTrainingCount(String.valueOf(powerId)), HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    @ResponseStatus(HttpStatus.OK)
    void doPowerTraining(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to increment power training count");
        service.doPowerTraining(String.valueOf(powerId));
    }

    @PutMapping("/reset/{id}")
    @ResponseStatus(HttpStatus.OK)
    void resetPowerStatistics(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to reset power training count");
        service.resetPowerStatistics(String.valueOf(powerId));
    }

    @PostMapping
    public ResponseEntity createNewStatistics(){
        LOGGER.info("Attempting to create new power training");
        return new ResponseEntity<>("Your gym id nr : " + service.createNewPowerStatistics(), HttpStatus.CREATED);
    }
}