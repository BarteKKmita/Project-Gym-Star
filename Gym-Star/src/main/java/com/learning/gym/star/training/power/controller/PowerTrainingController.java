package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController()
@RequestMapping("api/power")
public class PowerTrainingController {
    @Autowired
    private PowerTrainingService service;

    public PowerTrainingController(PowerTrainingService service){
        this.service = service;
    }

    @GetMapping("{id}")
    int getPowerTrainingCount(@PathVariable("id") int powerId){
        return service.getPowerTrainingCount(powerId);
    }

    @PutMapping("/train/{id}")
    void doPowerTraining(@PathVariable("id") int powerId){
        service.doPowerTraining(powerId);
    }

    @PutMapping("/reset/{id}")
    void resetPowerStatistics(@PathVariable("id") int powerId){
        service.resetPowerStatistics(powerId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStatistics(){
        String statisticsId = service.createNewPowerStatistics();
        return new ResponseEntity(statisticsId, HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity("There is no such power statistics in database.", HttpStatus.NOT_FOUND);
    }
}
