package com.learning.gym.star.gym.service.jdbc;

import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jdbc.GymRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJdbc")
public final class GymServiceJdbc {

    private static final Logger LOGGER = LoggerFactory.getLogger(GymServiceJpa.class);
    private final GymRepository gymRepository;
    private final GymSerializer gymSerializer;

    @Autowired
    public GymServiceJdbc(@Qualifier("gym database access") GymRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List<GymDTO> getAllGyms(){
        return gymSerializer.buildGymDTOFromGymAsStringList(gymRepository.getGymData());
    }

    public String addGym(GymDTO gym){
        LOGGER.info("Adding gym: {}.", gym);
        var gymEntity = gymSerializer.getGymFromGymGTO(gym);
        return gymRepository.add(gymEntity);
    }

    public void updateGym(GymDTO gym, int gymId){
        LOGGER.info("Updating gym: {} with id: {}.", gym, gymId);
        var gymEntity = gymSerializer.getGymFromGymGTO(gym);
        gymRepository.update(gymEntity, gymId);
    }

    public GymDTO getGymById(int gymId){
        LOGGER.info("Getting gym with id {}.", gymId);
        String[] gymAsStringArray = gymRepository.getGymDataById(gymId);
        LOGGER.info("Serializing and returning gym received from database");
        return gymSerializer.buildGymDTO(gymAsStringArray);
    }

    public void deleteGymById(int gymId){
        LOGGER.info("Updating gym with id: {}.", gymId);
        gymRepository.delete(gymId);
    }
}