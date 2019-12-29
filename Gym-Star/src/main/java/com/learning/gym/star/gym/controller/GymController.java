package com.learning.gym.star.gym.controller;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.service.GymService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/gym")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class GymController {

    private final GymService gymService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addGym ( @Valid @NotNull @RequestBody Gym gym ) {
        gymService.addGym(gym);
    }

    @GetMapping(path = {"/all"})
    public List <String> getAllGyms(){
        return gymService.getAllGyms();
    }

    @GetMapping(path = "{id}")
    public String[] getGymById ( @PathVariable("id") int gymId ) {
        return gymService.getGymByIdm(gymId);
    }

    @PutMapping
    public void updateGym ( @Valid @NotNull @RequestBody Gym gym ) {
        int gymId = Integer.parseInt(gym.getGymId());
        gymService.updateGym(gym, gymId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGymById ( @PathVariable("id") int gymId ) {
        gymService.deleteGymById(gymId);
    }
}


