package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sportsman")
public class SportsmanController {
    private SportsmanService service;

    public SportsmanController(SportsmanService service){
        this.service = service;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSportsman(@RequestBody SportsmanDTO sportsman){
        service.addSportsman(sportsman);
    }
}
