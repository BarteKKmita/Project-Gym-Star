package com.learning.gym.star.gym.controller;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.service.GymService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/gym")
@RestController
public class GymController{

    private final GymService gymService;

    public GymController(@Qualifier("GymServiceJpa") GymService gymService){
        this.gymService = gymService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addGym(@Valid @NotNull @RequestBody GymFrameForController gymFrame){
        return "Your gym id: " + gymService.addGym(gymFrame);
    }

    @GetMapping(path = {"/all"})
    public List <String> getAllGyms(){
        return gymService.getAllGyms();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        GymFrameForController gymFrame = gymService.getGymById(gymId);
        if(gymFrame != null) {
            return new ResponseEntity <>(gymFrame, HttpStatus.CREATED);
        } else {
            return new ResponseEntity <>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleContentNotAllowedException(){
        return new ResponseEntity <>("Record not found", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public void updateGym(@Valid @NotNull @RequestBody Gym gym){
        int gymId = Integer.parseInt(gym.getGymId());
        gymService.updateGym(gym, gymId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGymById(@PathVariable("id") int gymId){
        gymService.deleteGymById(gymId);
    }
}


