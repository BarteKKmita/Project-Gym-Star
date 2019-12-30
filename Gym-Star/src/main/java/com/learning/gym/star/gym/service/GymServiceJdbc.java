package com.learning.gym.star.gym.service;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import com.learning.gym.star.gym.database.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJdbc")
public class GymServiceJdbc implements GymService{

    private GymRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJdbc(@Qualifier("gym database access") GymRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List <String> getAllGyms(){
        return gymRepository.getGymData();
    }

    public String addGym(GymFrameForController gym){
        return gymRepository.add(gymSerializer.getGymFromGymFrame(gym));
    }

    public void updateGym(Gym gym, int gymId){
        gymRepository.update(gym, gymId);
    }

    public GymFrameForController getGymById(int gymId){
        String[] gymAsStringArray = gymRepository.getGymDataById(gymId);
        return buildGymForController(gymAsStringArray);
    }

    public void deleteGymById(int gymId){
        gymRepository.delete(gymId);
    }

    private GymFrameForController buildGymForController(String[] gymAsStringArray){
        return GymFrameForController.builder()
                .gymId(gymAsStringArray[0])
                .gymName(gymAsStringArray[1])
                .street(gymAsStringArray[2])
                .city(gymAsStringArray[3])
                .buildingNumber(gymAsStringArray[4])
                .build();
    }
}
