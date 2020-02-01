package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;

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

    @GetMapping("/dateandtime")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats(Long sportsmanPesel){
        return new ResponseEntity<>(service.getSportsmanStatistics(sportsmanPesel), HttpStatus.OK);
    }

    @GetMapping("/dateandtime2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats2(Long sportsmanPesel){
        return new ResponseEntity<>(service.getSportsmanStatistics2(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/gettrainer")
    @ResponseStatus(HttpStatus.CREATED)
    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        service.chooseTrainer(sportsmanPesel, trainerPesel);
    }

    @GetMapping("/mytrainer")
    public ResponseEntity getMyTrainer(Long sportsmanPesel){
        return new ResponseEntity<>(service.getMyTrainerData(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/trainCardio")
    public boolean trainCardio(Long sportsmanPesel){
        return service.trainCardio(sportsmanPesel);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("Sportsman with given pesel number already exists.", HttpStatus.CONFLICT);
    }
}
