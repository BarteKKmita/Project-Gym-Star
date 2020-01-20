package com.learning.gym.star.gym.service.jdbc;

import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.database.jdbc.GymRepository;
import com.learning.gym.star.gym.service.GymSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GymServiceJdbc")
public class GymServiceJdbc {

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
        return gymRepository.add(gymSerializer.getGymFromGymGTO(gym));
    }

    public void updateGym(GymDTO gym, int gymId){
        gymRepository.update(gymSerializer.getGymFromGymGTO(gym), gymId);
    }

    public GymDTO getGymById(int gymId){
        String[] gymAsStringArray = gymRepository.getGymDataById(gymId);
        return gymSerializer.buildGymDTO(gymAsStringArray);
    }

    public void deleteGymById(int gymId){
        gymRepository.delete(gymId);
    }
}
