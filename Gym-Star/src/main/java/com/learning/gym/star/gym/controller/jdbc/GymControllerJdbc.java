package com.learning.gym.star.gym.controller.jdbc;

import com.learning.gym.star.gym.controller.GymDTO;
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
        logger.info("Attempting to get all available gyms. {}", this.getClass());
        return new ResponseEntity<>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        logger.info("Attempting to get gym by id. {}", this.getClass());
        return new ResponseEntity<>(gymService.getGymById(gymId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addGym(@RequestBody GymDTO gymDTO){
        logger.info("Attempting to add gym to database. {}", this.getClass());
        return new ResponseEntity("Your gym id: " + gymService.addGym(gymDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @NotNull @RequestBody GymDTO gym){
        int gymId = Integer.parseInt(gym.getGymId());
        logger.info("Attempting to update gym. {}", this.getClass());
        gymService.updateGym(gym, gymId);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGymById(@PathVariable("id") int gymId){
        logger.info("Attempting to delete gym. {}", this.getClass());
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity<>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleContentNotAllowedException(){
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }
}
