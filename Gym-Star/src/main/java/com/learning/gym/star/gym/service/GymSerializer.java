package com.learning.gym.star.gym.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GymSerializer{
    private static ObjectMapper objectMapper = new ObjectMapper();

    public Gym getGymFromGymFrame(GymFrameForController gymFrame){
        String gymAsJson = "";
        try {
            gymAsJson = objectMapper.writeValueAsString(gymFrame);

        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        Gym databaseGym = null;
        try {
            databaseGym = objectMapper.readValue(gymAsJson, Gym.class);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return databaseGym;
    }

    public GymFrameForController getGymFrameFromGym(Gym databaseGym){
        String gymAsJson = "";
        try {
            gymAsJson = objectMapper.writeValueAsString(databaseGym);

        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        GymFrameForController gymFrame = null;
        try {
            gymFrame = objectMapper.readValue(gymAsJson, GymFrameForController.class);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return gymFrame;
    }
}
