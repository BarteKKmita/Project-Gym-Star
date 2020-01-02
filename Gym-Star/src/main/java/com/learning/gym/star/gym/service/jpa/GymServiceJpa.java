package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJpa")
public class GymServiceJpa{
    private GymJpaRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJpa(GymJpaRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List <String> getAllGyms(){
        return null;
    }

    public String addGym(GymFrameForController gym){
        return gymRepository.save(gymSerializer.getGymFromGymFrame(gym)).getGymId();
    }

    public void updateGym(Gym gym, int gymId){

    }

    public GymFrameForController getGymById(int gymId){
        Gym databaseGym = gymRepository.findById(Integer.toString(gymId)).orElse(null);
        return gymSerializer.getGymFrameFromGym(databaseGym);
    }

    public void deleteGymById(int gymId){
    }
}
