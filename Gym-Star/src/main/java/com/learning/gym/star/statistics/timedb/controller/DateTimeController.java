package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.statistics.timedb.service.DateTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(service.getSportsManDateAndTimeStatistics(String.valueOf(statisticsId)), HttpStatus.OK);
    }
}
