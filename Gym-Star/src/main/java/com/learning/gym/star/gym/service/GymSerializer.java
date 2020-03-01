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

    public Gym serializeGym(GymDTO gymDTO){
        LOGGER.info("Attempting to serialize gym with ID: {}", gymDTO.getGymId());
        return Gym.builder()
                .gymId(gymDTO.getGymId())
                .gymName(gymDTO.getGymName())
                .street(gymDTO.getStreet())
                .city(gymDTO.getCity())
                .buildingNumber(gymDTO.getBuildingNumber())
                .build();
    }

    public GymDTO deserializeGym(Gym gymEntity){
        LOGGER.info("Attempting to deserialize gym with ID: {}", gymEntity.getGymId());
        return GymDTO.builder()
                .gymId(gymEntity.getGymId())
                .gymName(gymEntity.getGymName())
                .street(gymEntity.getStreet())
                .city(gymEntity.getCity())
                .buildingNumber(gymEntity.getBuildingNumber())
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

    @Deprecated
    public List<GymDTO> buildGymDTOFromGymAsStringList(List<String> gymsFromDatabase){
        LOGGER.info("Serializing list of gyms form database to Gym list");
        List<GymDTO> gymListForController = new ArrayList<>();
        gymsFromDatabase.forEach(gym -> gymListForController.add(buildGymDTO(gym.split(" "))));
        return gymListForController;
    }
}