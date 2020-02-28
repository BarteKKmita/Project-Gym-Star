package com.learning.gym.star.gym.service;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class GymSerializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GymSerializer.class);

    public Gym getGymFromGymGTO(GymDTO gymDTO){
        LOGGER.info("Attempt to serialize GymDTO to GymEntity for gym ID: {}", gymDTO.getGymId());
        return Gym.builder()
                .gymId(gymDTO.getGymId())
                .gymName(gymDTO.getGymName())
                .street(gymDTO.getStreet())
                .city(gymDTO.getCity())
                .buildingNumber(gymDTO.getBuildingNumber())
                .build();
    }

    public GymDTO getGymDTOFromGym(Gym databaseGym){
        LOGGER.info("Attempt to serialize GymEntity to GymDTO for gym ID: {}", databaseGym.getGymId());
        return GymDTO.builder()
                .gymId(databaseGym.getGymId())
                .gymName(databaseGym.getGymName())
                .street(databaseGym.getStreet())
                .city(databaseGym.getCity())
                .buildingNumber(databaseGym.getBuildingNumber())
                .build();
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
        LOGGER.info("Serializing list of gyms form database to GymDTO list");
        List<GymDTO> gymListForController = new ArrayList<>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymDTO(gym.split(" "))));
        return gymListForController;
    }
}