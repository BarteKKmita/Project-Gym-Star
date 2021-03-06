package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import org.apache.logging.log4j.core.util.Integers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("GymServiceJpa")
public class GymServiceJpa {
    private static final Logger LOGGER = LoggerFactory.getLogger(GymServiceJpa.class);
    private final GymJpaRepository gymRepository;
    private final GymSerializer gymSerializer;

    public GymServiceJpa(GymJpaRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List<GymDTO> getAllGyms(){
        return gymSerializer.buildGymDTOListFromGymList(gymRepository.findAll());
    }

    public GymDTO getGymById(int gymId){
        LOGGER.info("Getting gym with id: {}.", gymId);
        var gymEntity = getGymEntity(gymId);
        return gymSerializer.deserializeGym(gymEntity);
    }

    public String addGym(GymDTO gymDTO){
        if (gymDTO.getGymId() != null) {
            return "";
        }
        LOGGER.info("Adding gymDTO: {}.", gymDTO);
        var gymEntity = gymSerializer.serializeGym(gymDTO);
        return gymRepository.saveAndFlush(gymEntity).getGymId();
    }

    public void updateGym(GymDTO gymDTO){
        if (gymDTO.getGymId() == null) {
            LOGGER.error("Updating gym requires specifying id.");
            throw new IncorrectUpdateSemanticsDataAccessException("Gym id cannot be null");
        }
        getGymEntity(Integers.parseInt(gymDTO.getGymId()));
        LOGGER.info("Updating gym with gym id {}.", gymDTO.getGymId());
        var gymEntity = gymSerializer.serializeGym(gymDTO);
        gymRepository.saveAndFlush(gymEntity);
    }

    public void deleteGymById(String gymId) {
        LOGGER.info("Deleting gym with id: {}.", gymId);
        gymRepository.deleteById(gymId);
    }

    private Gym getGymEntity(int gymId) {
        return gymRepository.findById(Integer.toString(gymId))
                .orElseThrow(() -> {
                    LOGGER.info("Gym with id {} does not exists", gymId);
                    throw new NoSuchElementException("Gym with id: " + gymId + " does not exists");
                });
    }
}