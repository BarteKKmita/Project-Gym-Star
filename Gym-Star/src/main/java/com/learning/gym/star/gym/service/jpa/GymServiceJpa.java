package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import com.learning.gym.star.gym.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJpa")
public class GymServiceJpa implements GymService{
    private GymJpaRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJpa(GymJpaRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    @Override
    public List <String> getAllGyms(){
        return null;
    }

    @Override
    public String addGym(GymFrameForController gym){
        return gymRepository.save(gymSerializer.getGymFromGymFrame(gym)).getGymId();
    }

    @Override
    public void updateGym(Gym gym, int gymId){

    }

    @Override
    public GymFrameForController getGymById(int gymId){
        Gym databaseGym = gymRepository.findById(Integer.toString(gymId)).orElse(null);
        return gymSerializer.getGymFrameFromGym(databaseGym);
    }

    @Override
    public void deleteGymById(int gymId){
    }
}
