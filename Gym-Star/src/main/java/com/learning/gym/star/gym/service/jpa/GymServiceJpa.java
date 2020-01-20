package com.learning.gym.star.gym.service.jpa;

import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJpa")
@NoArgsConstructor
public class GymServiceJpa {
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
        Gym databaseGym = gymRepository.findById(Integer.toString(gymId)).orElseThrow();
        return gymSerializer.getGymDTOFromGym(databaseGym);
    }

    public String addGym(GymDTO gym){
        if (gym.getGymId() != null) {
            return "";
        }
        return gymRepository.saveAndFlush(gymSerializer.getGymFromGymGTO(gym)).getGymId();
    }

    public void updateGym(GymDTO gymDTO){
        if (gymDTO.getGymId() == null) {
            throw new org.springframework.dao.IncorrectUpdateSemanticsDataAccessException("Gym id cannot be null");
        }
        gymRepository.saveAndFlush(gymSerializer.getGymFromGymGTO(gymDTO));
    }

    public void deleteGymById(String gymId){
        gymRepository.deleteById(gymId);
    }
}
