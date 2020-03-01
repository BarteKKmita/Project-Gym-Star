package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.NoSuchElementException;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@RestController
@RequestMapping("api/jpa/gym")
public class GymControllerJpa {
    private static final Logger LOGGER = LoggerFactory.getLogger(GymControllerJpa.class);
    private final GymServiceJpa gymService;

    public GymControllerJpa(GymServiceJpa gymService){
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity addGym(@Valid @RequestBody GymDTO gymDTO){
        LOGGER.info("Attempting to add gym to database.");
        String gymId = gymService.addGym(gymDTO);
        if (gymId.isEmpty()) {
            var message = "Gym data was provided with gym id. It should be empty since app generates id automatically";
            LOGGER.info(message);
            return new ResponseEntity<>(message, getResponseDateAndTime(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Your gym id: " + gymId, getResponseDateAndTime(), HttpStatus.CREATED);
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        LOGGER.info("Attempting to get all available gyms.");
        return new ResponseEntity<>(gymService.getAllGyms(), getResponseDateAndTime(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        LOGGER.info("Attempting to get gym by id.");
        var gymDTO = gymService.getGymById(gymId);
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-type", "application/json");
        header.addAll(getResponseDateAndTime());
        return new ResponseEntity<>(gymDTO, header, HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @RequestBody GymDTO gymDTO){
        LOGGER.info("Attempting to update gym.");
        gymService.updateGym(gymDTO);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGymById(@PathVariable("id") int gymId){
        LOGGER.info("Attempting to delete gym.");
        gymService.deleteGymById(String.valueOf(gymId));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleWrongGymDTO(){
        return new ResponseEntity<>("Entered wrong gym data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleEmptyResult(EmptyResultDataAccessException exception){
        LOGGER.info(exception.getMessage());
        return new ResponseEntity<>("Gym with given id does not exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectUpdateSemanticsDataAccessException.class)
    public ResponseEntity handleWrongUpdateStatement(IncorrectUpdateSemanticsDataAccessException exception){
        LOGGER.info("Updating gym requires specifying id. Status 400 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericJDBCException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity<>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(NoSuchElementException exception){
        LOGGER.info("Gym with given id does not exists. Status 404 returned.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private MultiValueMap<String, String> getResponseDateAndTime(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("date", LocalDateTime.now().format(ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)));
        return headers;
    }
}