package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController()
@RequestMapping("api/power")
public class PowerTrainingController {

    private PowerTrainingService service;

    public PowerTrainingController(PowerTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity getPowerTrainingCount(@PathVariable("id") int powerId){
        return new ResponseEntity<>(service.getPowerTrainingCount(powerId), HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    @ResponseStatus(HttpStatus.OK)
    void doPowerTraining(@PathVariable("id") int powerId){
        service.doPowerTraining(powerId);
    }

    @PutMapping("/reset/{id}")
    @ResponseStatus(HttpStatus.OK)
    void resetPowerStatistics(@PathVariable("id") int powerId){
        service.resetPowerStatistics(powerId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStatistics(){
        return new ResponseEntity<>("Your gym id nr : " + service.createNewPowerStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("There is no such power statistics in database.", HttpStatus.NOT_FOUND);
    }
}
