package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.hibernate.validator.constraints.pl.PESEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/sportsman")
public class SportsmanController {
    private final SportsmanService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsmanController.class);
    private static final String WRONG_PESEL_MESSAGE = "Pesel must have proper number of digits and has to be valid";

    public SportsmanController(SportsmanService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSportsman(@Valid @RequestBody SportsmanDTO sportsman){
        LOGGER.info("Attempting to add new sportsman.");
        service.addSportsman(sportsman);
    }

    @GetMapping("/{pesel}/date-time-stats")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getSportsmanDateAndTimeStats(@PathVariable("pesel")
                                                       @PESEL(message = WRONG_PESEL_MESSAGE) String sportsmanPesel){
        LOGGER.info("Attempting to get sportsman date and time statistics.");
        return new ResponseEntity<>(service.getSportsmanStatistics(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/{pesel}/trainer")
    @ResponseStatus(HttpStatus.CREATED)
    public void chooseTrainer(@PESEL(message = WRONG_PESEL_MESSAGE) @PathVariable("pesel") String sportsmanPesel,
                              @PESEL(message = WRONG_PESEL_MESSAGE) @RequestBody String trainerPesel){
        LOGGER.info("Attempting to choose trainer.");
        service.chooseTrainer(sportsmanPesel, trainerPesel);
    }

    @GetMapping("/{pesel}/trainer")
    public ResponseEntity getMyTrainer(@PathVariable("pesel")
                                       @PESEL(message = WRONG_PESEL_MESSAGE) String sportsmanPesel){
        LOGGER.info("Attempting to display sportsman's.");
        return new ResponseEntity<>(service.getMyTrainerData(sportsmanPesel), HttpStatus.OK);
    }

    @PutMapping("/{pesel}/cardio/train")
    @ResponseStatus(HttpStatus.OK)
    public void trainCardio(@PathVariable("pesel")
                            @PESEL(message = WRONG_PESEL_MESSAGE) String sportsmanPesel){
        LOGGER.info("Attempting to do cardio training.");
        service.trainCardio(sportsmanPesel);
    }

    @PutMapping("/{pesel}/power/train")
    @ResponseStatus(HttpStatus.OK)
    public void trainPower(@PathVariable("pesel")
                           @PESEL(message = WRONG_PESEL_MESSAGE) String sportsmanPesel){
        LOGGER.info("Attempting to do power training.");
        service.trainPower(sportsmanPesel);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity handleExistingRecordInDatabase(EntityExistsException exception){
        LOGGER.info("Sportsman with specified pesel already exists. Status 409 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity handleEnteringWrongGender(InvalidFormatException exception){
        LOGGER.info("Entered wrong gender during creation of Sportsman. Status 406 returned.");
        return new ResponseEntity<>("Entered invalid gender: " + exception.getValue() + ". Valid gender is M or F.",
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity handleChoosingNotExistingTrainer(NoResultException exception){
        LOGGER.info("There is no trainer with specified pesel.");
        return new ResponseEntity<>("There is no trainer with specified pesel.",
                HttpStatus.NOT_FOUND);
    }
}

