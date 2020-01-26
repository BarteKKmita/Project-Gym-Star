package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJpa")
@NoArgsConstructor
public class GymServiceJpa {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private GymJpaRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJpa(GymJpaRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List<GymDTO> getAllGyms(){
        return gymSerializer.buildGymDTOListFromGymList(gymRepository.findAll());
    }

    public GymDTO getGymById(int gymId){
        logger.debug("Getting gym with id: {}. {}", gymId, this.getClass());
        Gym databaseGym = gymRepository.findById(Integer.toString(gymId)).orElseThrow();
        return gymSerializer.getGymDTOFromGym(databaseGym);
    }

    public String addGym(GymDTO gym){
        logger.debug("Adding gym: {}. {}", gym, this.getClass());
        if (gym.getGymId() != null) {
            return "";
        }
        return gymRepository.saveAndFlush(gymSerializer.getGymFromGymGTO(gym)).getGymId();
    }

    public void updateGym(GymDTO gymDTO){
        logger.debug("Updating gym with gym id {}. {}", gymDTO.getGymId(), this.getClass());
        if (gymDTO.getGymId() == null) {
            logger.error("Updating gym requires specifying id. {}", this.getClass());
            throw new org.springframework.dao.IncorrectUpdateSemanticsDataAccessException("Gym id cannot be null");
        }
        gymRepository.saveAndFlush(gymSerializer.getGymFromGymGTO(gymDTO));
    }

    public void deleteGymById(String gymId){
        logger.debug("Deleting gym with id: {}. {}", gymId, this.getClass());
        gymRepository.deleteById(gymId);
    }
}
