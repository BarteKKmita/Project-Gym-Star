package com.learning.gym.star.gym.controller.jdbc;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import com.learning.gym.star.gym.service.jdbc.GymServiceJdbc;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@RequestMapping("api/jdbc/gym")
@RestController
public class GymControllerJdbc{

    private final GymServiceJdbc gymService;

    public GymControllerJdbc(GymServiceJdbc gymService){
        this.gymService = gymService;
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        return new ResponseEntity <>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        return new ResponseEntity <>(gymService.getGymById(gymId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addGym(@RequestBody GymFrameForController gymFrame){
        return new ResponseEntity("Your gym id: " + gymService.addGym(gymFrame), HttpStatus.OK);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGym(@Valid @NotNull @RequestBody Gym gym){
        int gymId = Integer.parseInt(gym.getGymId());
        gymService.updateGym(gym, gymId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGymById(@PathVariable("id") int gymId){
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleWrongTypeInHttpMethod(){
        return new ResponseEntity <>("One of given parameter has a wrong type.", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleContentNotAllowedException(){
        return new ResponseEntity <>("Record not found", HttpStatus.BAD_REQUEST);
    }
}
