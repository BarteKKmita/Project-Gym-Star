package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController()
@RequestMapping("api/power")
public final class PowerTrainingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrainingController.class);
    private PowerTrainingService service;

    public PowerTrainingController(PowerTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity getPowerTrainingCount(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to get power training count");
        return new ResponseEntity<>(service.getPowerTrainingCount(powerId), HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    @ResponseStatus(HttpStatus.OK)
    void doPowerTraining(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to increment power training count");
        service.doPowerTraining(powerId);
    }

    @PutMapping("/reset/{id}")
    @ResponseStatus(HttpStatus.OK)
    void resetPowerStatistics(@PathVariable("id") int powerId){
        LOGGER.info("Attempting to reset power training count");
        service.resetPowerStatistics(powerId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStatistics(){
        LOGGER.info("Attempting to create new power training");
        return new ResponseEntity<>("Your gym id nr : " + service.createNewPowerStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("There is no such power statistics in database.", HttpStatus.NOT_FOUND);
    }
}
