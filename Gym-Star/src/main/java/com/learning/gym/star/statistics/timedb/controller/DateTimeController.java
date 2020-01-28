package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import com.learning.gym.star.statistics.timedb.service.DateTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/date&time")
public class DateTimeController {
    private DateTimeService service;

    public DateTimeController(DateTimeService service){
        this.service = service;
    }

    @PostMapping("/save/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTrainingDateAndTime(@PathVariable("id") int statisticsId){
        service.addTrainingDateAndTime(statisticsId);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getSportsmanDateAndTimeStats(@PathVariable("id") int statisticsId){
        List<TrainingDateStatisticsDB> trainingDateTimeStats = service.getSportsManDateAndTimeStatistics(statisticsId);
        if (trainingDateTimeStats.isEmpty()) {
            return new ResponseEntity<>("There is no training date and time statistics for this sportsman", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingDateTimeStats, HttpStatus.OK);
    }
}
