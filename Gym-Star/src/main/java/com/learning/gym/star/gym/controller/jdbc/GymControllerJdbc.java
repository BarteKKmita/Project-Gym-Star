package com.learning.gym.star.gym.controller.jdbc;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrame;
import com.learning.gym.star.gym.service.jdbc.GymServiceJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@RequestMapping("api/jdbc/gym")
@RestController
public class GymControllerJdbc {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GymServiceJdbc gymService;

    public GymControllerJdbc(GymServiceJdbc gymService){
        this.gymService = gymService;
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        logger.info("Attempting to get all available gyms.");
        return new ResponseEntity <>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        logger.info("Attempting to get gym by id.");
        return new ResponseEntity <>(gymService.getGymById(gymId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addGym(@RequestBody GymFrame gymFrame){
        logger.info("Attempting to add gym to database.");
        return new ResponseEntity("Your gym id: " + gymService.addGym(gymFrame), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @NotNull @RequestBody Gym gym){
        logger.info("Attempting to update gym.");
        int gymId = Integer.parseInt(gym.getGymId());
        gymService.updateGym(gym, gymId);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGymById(@PathVariable("id") int gymId){
        logger.info("Attempting to delete gym.");
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity <>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleContentNotAllowedException(){
        return new ResponseEntity <>("Record not found", HttpStatus.NOT_FOUND);
    }
}
