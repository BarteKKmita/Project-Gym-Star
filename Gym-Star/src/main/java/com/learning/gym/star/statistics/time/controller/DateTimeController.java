package com.learning.gym.star.statistics.time.controller;

import com.learning.gym.star.statistics.time.TrainingDateStatisticsDB;
import com.learning.gym.star.statistics.time.service.DateTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/date&time")
public class DateTimeController {
    private DateTimeService service;

    public DateTimeController(DateTimeService service){
        this.service = service;
    }

    @PostMapping("/save/{id}")
    public TrainingDateStatisticsDB saveTrainingDateAndTime(@PathVariable("id") int statisticsId){
        return service.addTrainingDateAndTime(statisticsId);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getSportsmanDateAndTimeStats(@PathVariable("id") int statisticsId){
        return new ResponseEntity<>(service.getSportsManDateAndTimeStatistics(statisticsId), HttpStatus.OK);
    }
}
