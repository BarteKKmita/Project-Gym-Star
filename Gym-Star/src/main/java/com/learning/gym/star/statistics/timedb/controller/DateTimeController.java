package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.statistics.timedb.service.DateTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datetime")
public final class DateTimeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeController.class);
    private final DateTimeService service;

    public DateTimeController(DateTimeService service){
        this.service = service;
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTrainingDateAndTime(@PathVariable("id") int statisticsId){
        LOGGER.info("Attempting to save training date and time.");
        service.addTrainingDateAndTime(String.valueOf(statisticsId));
    }

    @GetMapping("{id}")
    public ResponseEntity getSportsmanDateAndTimeStats(@PathVariable("id") int statisticsId){
        LOGGER.info("Attempting to get sportsman training date and time.");
        List<TrainingDateStatisticsEntity> trainingDateTimeStats = service.getSportsManDateAndTimeStatistics(String.valueOf(statisticsId));
        if (trainingDateTimeStats == null || trainingDateTimeStats.isEmpty()) {
            LOGGER.info("There is no training date and time statistics for this sportsman statistics id: {}. Status 404 returned.", statisticsId);
            return new ResponseEntity<>("There is no training date and time statistics for this sportsman", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingDateTimeStats, HttpStatus.OK);
    }
}
