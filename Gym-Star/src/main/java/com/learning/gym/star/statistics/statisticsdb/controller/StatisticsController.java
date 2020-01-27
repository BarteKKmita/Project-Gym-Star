package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private StatisticsService service;

    public StatisticsController(StatisticsService service){
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity getAllStatistics(){
        return new ResponseEntity<>(service.readAllStatistics(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getStatisticsById(@PathVariable("id") int statisticsId){
        return new ResponseEntity<>(service.readStatisticsById(statisticsId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStatistics(){
        return new ResponseEntity<>(service.createNewStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("There is no such statistics in database.", HttpStatus.NOT_FOUND);
    }
}