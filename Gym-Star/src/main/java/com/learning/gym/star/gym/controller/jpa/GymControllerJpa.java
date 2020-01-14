package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymFrame;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@RequestMapping("api/jpa/gym")
@RestController
public class GymControllerJpa {

    private final GymServiceJpa gymService;

    public GymControllerJpa(GymServiceJpa gymService){
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity addGym(@Valid @NotNull @RequestBody GymFrame gymFrame){
        String gymId = gymService.addGym(gymFrame);
        if (gymId.isEmpty()) {
            return new ResponseEntity("Specified gym id already exists ", HttpStatus.CONFLICT);
        }
        return new ResponseEntity("Your gym id: " + gymId, HttpStatus.CREATED);
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        return new ResponseEntity<>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        GymFrame gymFrame = gymService.getGymById(gymId);
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-type", "application/json");
        if (gymFrame != null) {
            return new ResponseEntity<>(gymFrame, header, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @RequestBody GymFrame gymFrame){
        gymService.updateGym(gymFrame);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGymById(@PathVariable("id") String gymId){
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleEmptyResult(){
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectUpdateSemanticsDataAccessException.class)
    public ResponseEntity handleWrongUpdateStatement(){
        return new ResponseEntity<>("Gym id cannot be null", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericJDBCException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity<>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchRecordInDatabase(){
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }
}


