package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.Gym;
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
import java.util.List;

@RequestMapping("api/gym")
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
    public List <String> getAllGyms(){
        return gymService.getAllGyms();
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


