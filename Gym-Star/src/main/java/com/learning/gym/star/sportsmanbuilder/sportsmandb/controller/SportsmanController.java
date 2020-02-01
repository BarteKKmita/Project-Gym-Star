package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("/api/sportsman")
public class SportsmanController {
    private SportsmanService service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SportsmanController(SportsmanService service){
        this.service = service;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSportsman(@RequestBody SportsmanDTO sportsman){
        logger.info("Attempting to add new sportsman.");
        service.addSportsman(sportsman);
    }

    @GetMapping("/dateandtime")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats2(Long sportsmanPesel){
        logger.info("Attempting to get sportsman date and time statistics.");
        return new ResponseEntity<>(service.getSportsmanStatistics(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/gettrainer")
    @ResponseStatus(HttpStatus.CREATED)
    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        logger.info("Attempting to choose trainer.");
        service.chooseTrainer(sportsmanPesel, trainerPesel);
    }

    @GetMapping("/mytrainer")
    public ResponseEntity getMyTrainer(Long sportsmanPesel){
        logger.info("Attempting to display sportsman's.");
        return new ResponseEntity<>(service.getMyTrainerData(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/trainCardio")
    public boolean trainCardio(Long sportsmanPesel){
        logger.info("Attempting to do cardio training.");
        return service.trainCardio(sportsmanPesel);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("Sportsman with given pesel number already exists.", HttpStatus.CONFLICT);
    }
}
