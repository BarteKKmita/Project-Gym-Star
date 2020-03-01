package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/sportsman")
public final class SportsmanController {
    private final SportsmanService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsmanController.class);

    public SportsmanController(SportsmanService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSportsman(@RequestBody SportsmanDTO sportsman){
        LOGGER.info("Attempting to add new sportsman.");
        service.addSportsman(sportsman);
    }

    @GetMapping("/datetime")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats(Long sportsmanPesel){
        LOGGER.info("Attempting to get sportsman date and time statistics.");
        return new ResponseEntity<>(service.getSportsmanStatistics(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/gettrainer")
    @ResponseStatus(HttpStatus.CREATED)
    public void chooseTrainer(Long sportsmanPesel, Long trainerPesel){
        LOGGER.info("Attempting to choose trainer.");
        service.chooseTrainer(sportsmanPesel, trainerPesel);
    }

    @GetMapping("/mytrainer")
    public ResponseEntity getMyTrainer(Long sportsmanPesel){
        LOGGER.info("Attempting to display sportsman's.");
        return new ResponseEntity<>(service.getMyTrainerData(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/trainCardio")
    @ResponseStatus(HttpStatus.OK)
    public void trainCardio(Long sportsmanPesel){
        LOGGER.info("Attempting to do cardio training.");
        service.trainCardio(sportsmanPesel);
    }

    @PutMapping("/trainPower")
    @ResponseStatus(HttpStatus.OK)
    public void trainPower(Long sportsmanPesel){
        LOGGER.info("Attempting to do power training.");
        service.trainPower(sportsmanPesel);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity handleExistingRecordInDatabase(EntityExistsException exception){
        LOGGER.info("Sportsman with specified pesel already exists. Status 409 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(NoSuchElementException exception){
        LOGGER.info("Sportsman with given pesel not exists. Status 404 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}