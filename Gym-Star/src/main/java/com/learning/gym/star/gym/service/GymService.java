package com.learning.gym.star.gym.service;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService{
    private GymRepository gymRepository;

    @Autowired
    public GymService(@Qualifier("gym database access") GymRepository gymRepository){
        this.gymRepository = gymRepository;
    }

    public List <String> getAllGyms(){
        return gymRepository.getGymData();
    }

    public void addGym(Gym gym){
        gymRepository.add(gym);
    }

    public void updateGym(Gym gym, int gymId){
        gymRepository.update(gym, gymId);
    }

    public String[] getGymByIdm(int gymId){
        return gymRepository.getGymDataById(gymId);
    }

    public void deleteGymById(int gymId){
        gymRepository.delete(gymId);
    }
}
