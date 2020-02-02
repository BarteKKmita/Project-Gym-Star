package com.learning.gym.star.gym.service.jdbc;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jdbc.GymRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJdbc")
public class GymServiceJdbc {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private GymRepository gymRepository;
    private GymSerializer gymSerializer;

    @Autowired
    public GymServiceJdbc(@Qualifier("gym database access") GymRepository gymRepository, GymSerializer gymSerializer){
        this.gymRepository = gymRepository;
        this.gymSerializer = gymSerializer;
    }

    public List<GymDTO> getAllGyms(){
        return gymSerializer.buildGymDTOFromGymAsStringList(gymRepository.getGymData());
    }

    public String addGym(GymDTO gym){
        logger.debug("Adding gym: {}. {}", gym, this.getClass());
        Gym gymDB = gymSerializer.getGymFromGymGTO(gym);
        if (gymDB == null) {
            throw new IllegalArgumentException();
        } else {
            return gymRepository.add(gymDB);
        }
    }

    public void updateGym(GymDTO gym, int gymId){
        logger.debug("Updating gym: {} with id: {}. {}", gym, gymId, this.getClass());
        Gym gymDB = gymSerializer.getGymFromGymGTO(gym);
        if (gymDB == null) {
            throw new IllegalArgumentException();
        } else {
            gymRepository.update(gymDB, gymId);
        }
    }

    public GymDTO getGymById(int gymId){
        logger.debug("Getting gym with id {}. {}", gymId, this.getClass());
        String[] gymAsStringArray = gymRepository.getGymDataById(gymId);
        return gymSerializer.buildGymDTO(gymAsStringArray);
    }

    public void deleteGymById(int gymId){
        logger.debug("Updating gym with id: {}. {}", gymId, this.getClass());
        gymRepository.delete(gymId);
    }
}
