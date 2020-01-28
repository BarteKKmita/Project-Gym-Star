package com.learning.gym.star.gym.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GymSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Gym getGymFromGymGTO(GymDTO gymDTO){
        String gymAsJson = "";
        try {
            gymAsJson = objectMapper.writeValueAsString(gymDTO);
        } catch (JsonProcessingException e) {
            logger.error("Serialization of gym failure.", e);
        }
        Gym databaseGym = null;
        try {
            databaseGym = objectMapper.readValue(gymAsJson, Gym.class);
        } catch (JsonProcessingException e) {
            logger.error("Deserialization of gym failure.", e);
        }
        return databaseGym;
    }

    public GymDTO getGymDTOFromGym(Gym databaseGym){
        String gymAsJson = "";
        try {
            gymAsJson = objectMapper.writeValueAsString(databaseGym);

        } catch (JsonProcessingException e) {
            logger.error("Serialization of gym failure.", e);
        }
        GymDTO gymDTO = null;
        try {
            gymDTO = objectMapper.readValue(gymAsJson, GymDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("Deserialization of gym failure.", e);
        }
        return gymDTO;
    }

    public GymDTO buildGymDTO(String[] gymAsStringArray){
        int gymIdIndex = 0;
        int gymNameIndex = 1;
        int gymStreetIndex = 2;
        int gymCityIndex = 3;
        int gymBuildingNumberIndex = 4;
        return GymDTO.builder()
                .gymId(gymAsStringArray[gymIdIndex])
                .gymName(gymAsStringArray[gymNameIndex])
                .street(gymAsStringArray[gymStreetIndex])
                .city(gymAsStringArray[gymCityIndex])
                .buildingNumber(gymAsStringArray[gymBuildingNumberIndex])
                .build();
    }

    public List<GymDTO> buildGymDTOListFromGymList(List<Gym> gymsFromDatabase){
        List<GymDTO> gymListForController = new ArrayList<>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymDTO(gym.toStringArray())));
        return gymListForController;
    }

    public List<GymDTO> buildGymDTOFromGymAsStringList(List<String> gymsFromDatabase){
        List<GymDTO> gymListForController = new ArrayList<>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymDTO(gym.split(" "))));
        return gymListForController;
    }
}
