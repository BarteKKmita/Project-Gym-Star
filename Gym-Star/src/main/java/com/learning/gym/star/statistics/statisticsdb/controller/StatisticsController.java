package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    int createNewStatistics(){
        return service.createNewStatistics();
    }
}