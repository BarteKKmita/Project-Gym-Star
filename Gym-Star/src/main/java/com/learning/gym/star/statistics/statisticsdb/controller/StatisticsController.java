package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(service.readStatisticsById(String.valueOf(statisticsId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNewStatistics(){
        LOGGER.info("Attempting to get create new statistics.");
        return new ResponseEntity<>(service.createNewStatistics(), HttpStatus.CREATED);
    }
}