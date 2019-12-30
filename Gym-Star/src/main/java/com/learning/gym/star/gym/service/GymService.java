package com.learning.gym.star.gym.service;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;

import java.util.List;

public interface GymService{
    public List <String> getAllGyms();

    public String addGym(GymFrameForController gym);

    public void updateGym(Gym gym, int gymId);

    public GymFrameForController getGymById(int gymId);

    public void deleteGymById(int gymId);
}
