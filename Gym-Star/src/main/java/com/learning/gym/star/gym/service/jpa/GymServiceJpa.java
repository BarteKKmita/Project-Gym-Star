package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrame;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service("GymServiceJpa")
    public class GymServiceJpa {
    private GymJpaRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJpa(GymJpaRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List <GymFrame> getAllGyms(){
        return gymSerializer.buildGymListForController(gymRepository.findAll());
    }

    public GymFrame getGymById(int gymId){
        Gym databaseGym = gymRepository.findById(Integer.toString(gymId)).orElseThrow();
        return gymSerializer.getGymFrameFromGym(databaseGym);
    }

    public String addGym(GymFrame gym){
        if (gym.getGymId() != null) {
            return "";
        }
        return gymRepository.saveAndFlush(gymSerializer.getGymFromGymFrame(gym)).getGymId();
    }

    public void updateGym(GymFrame gymFrame){
        if (gymFrame.getGymId() == null) {
            throw new org.springframework.dao.IncorrectUpdateSemanticsDataAccessException("Gym id cannot be null");
        }
        gymRepository.saveAndFlush(gymSerializer.getGymFromGymFrame(gymFrame));
    }

    public void deleteGymById(String gymId){
        gymRepository.deleteById(gymId);
    }
}
