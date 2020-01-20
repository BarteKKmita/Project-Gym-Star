package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private StatisticsService service;

    public StatisticsController(StatisticsService service){
        this.service = service;
    }

    @PostMapping("/create")
    int createNewStatistics(){
        return service.createNewStatistics();
    }
}
