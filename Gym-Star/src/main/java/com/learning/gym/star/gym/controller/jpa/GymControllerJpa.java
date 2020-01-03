package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymFrameForController;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("api/jpa/gym")
@RestController
public class GymControllerJpa{

    private final GymServiceJpa gymService;

    public GymControllerJpa(GymServiceJpa gymService){
        this.gymService = gymService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addGym(@Valid @NotNull @RequestBody GymFrameForController gymFrame){
        return "Your gym id: " + gymService.addGym(gymFrame);
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity getAllGyms(){
        return new ResponseEntity <>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getGymById(@PathVariable("id") int gymId){
        GymFrameForController gymFrame = gymService.getGymById(gymId);
        MultiValueMap <String, String> header = new HttpHeaders();
        header.add("Content-type", "application/json");
        if(gymFrame != null) {
            return new ResponseEntity <>(gymFrame, header, HttpStatus.CREATED);
        } else {
            return new ResponseEntity <>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public void updateGym(@Valid @NotNull @RequestBody GymFrameForController gymFrame){
        gymService.updateGym(gymFrame);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGymById(@PathVariable("id") String gymId){
        gymService.deleteGymById(gymId);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleContentNotAllowedException(){
        return new ResponseEntity <>("Record not found", HttpStatus.BAD_REQUEST);
    }
}


