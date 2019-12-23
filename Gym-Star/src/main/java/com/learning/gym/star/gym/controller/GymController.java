package com.learning.gym.star.gym.controller;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.service.GymService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/gym")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class GymController {

    private final GymService gymService;

    @PostMapping()
    public void addPerson ( @Valid @NotNull @RequestBody Gym gym ) {
        gymService.addGym(gym);
    }

    @GetMapping()
    public List <String> getAllGyms () {
        return gymService.getAllGyms();
    }

}


