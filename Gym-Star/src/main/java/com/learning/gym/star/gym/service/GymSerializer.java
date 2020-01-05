package com.learning.gym.star.gym.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymFrameForController;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GymSerializer{
    private static ObjectMapper objectMapper = new ObjectMapper();

    public Gym getGymFromGymFrame(GymFrameForController gymFrame){
        String gymAsJson = "";
        try {
            gymAsJson = objectMapper.writeValueAsString(gymFrame);
        } catch(JsonProcessingException e) {
            System.out.println("Serialization of gym failure.");
            e.printStackTrace();
        }
        Gym databaseGym = null;
        try {
            databaseGym = objectMapper.readValue(gymAsJson, Gym.class);
        } catch(JsonProcessingException e) {
            System.out.println("Deserialization of gym failure.");
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

    public GymFrameForController buildGymFrameForController(String[] gymAsStringArray){
        return GymFrameForController.builder()
                .gymId(gymAsStringArray[0])
                .gymName(gymAsStringArray[1])
                .street(gymAsStringArray[2])
                .city(gymAsStringArray[3])
                .buildingNumber(gymAsStringArray[4])
                .build();
    }

    public List <GymFrameForController> buildGymListForController(List <Gym> gymsFromDatabase){
        List <GymFrameForController> gymListForController = new ArrayList <>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymFrameForController(gym.toStringArray())));
        return gymListForController;
    }

    public List <GymFrameForController> buildGymForControllerFromStringList(List <String> gymsFromDatabase){
        List <GymFrameForController> gymListForController = new ArrayList <>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymFrameForController(gym.split(" "))));
        return gymListForController;
    }
}
