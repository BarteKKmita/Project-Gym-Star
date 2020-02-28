package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("TrainerService")
public final class TrainerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);
    private final TrainerRepository repository;
    private final TrainerSerializer serializer;

    public TrainerService(TrainerRepository repository, TrainerSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addTrainer(TrainerDTO trainer) throws IOException{
        LOGGER.debug("Adding trainer to database. {}", trainer);
        repository.saveAndFlush(serializer.getTrainerFromTrainerDTO(trainer));
    }

    public List<TrainerDTO> getAllTrainers(){
        List<TrainerDTO> trainerList = new ArrayList<>();
        repository.findAll().forEach(trainerEntity -> {
            try {
                var trainerDTO = serializer.getTrainerDTOFromTrainer(trainerEntity);
                trainerList.add(trainerDTO);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        });
        return trainerList;
    }
}