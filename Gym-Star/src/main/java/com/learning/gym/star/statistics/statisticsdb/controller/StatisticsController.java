package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);
    private final StatisticsService service;

    public StatisticsController(StatisticsService service){
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity getAllStatistics(){
        LOGGER.info("Attempting to get all statistics training count");
        return new ResponseEntity<>(service.readAllStatistics(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getStatisticsById(@PathVariable("id") int statisticsId){
        LOGGER.info("Attempting to get statistics by id.");
        return new ResponseEntity<>(service.readStatisticsById(statisticsId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStatistics(){
        LOGGER.info("Attempting to get create new statistics.");
        return new ResponseEntity<>(service.createNewStatistics(), HttpStatus.CREATED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("There is no such statistics in database.", HttpStatus.NOT_FOUND);
    }
}