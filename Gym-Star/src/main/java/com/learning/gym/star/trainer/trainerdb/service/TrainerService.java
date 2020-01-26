package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.GsonTrainerSerializer;
import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("trainer service")
public class TrainerService {

    private TrainerRepository repository;

    private GsonTrainerSerializer serializer;

    public TrainerService(TrainerRepository repository, GsonTrainerSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addTrainer(TrainerDTO trainer){
        repository.saveAndFlush(serializer.getTrainerDBFromTrainerDTO(trainer));
    }

    public List<TrainerDTO> getAllTrainers(){
        List<TrainerDTO> trainerList = new ArrayList<>();
        repository.findAll().forEach(trainerDB -> trainerList.add(serializer.getTrainerDTOFromTrainer(trainerDB)));
        return trainerList;
    }
}