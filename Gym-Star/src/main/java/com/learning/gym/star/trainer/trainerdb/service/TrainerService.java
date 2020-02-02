package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("trainer service")
public class TrainerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrainerRepository repository;
    private TrainerSerializer serializer;

    public TrainerService(TrainerRepository repository, TrainerSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addTrainer(TrainerDTO trainer){
        logger.debug("Adding trainer to database. {}", trainer);
        repository.saveAndFlush(serializer.getTrainerDBFromTrainerDTO(trainer));
    }

    public List<TrainerDTO> getAllTrainers(){
        List<TrainerDTO> trainerList = new ArrayList<>();
        repository.findAll().forEach(trainerDB -> {
            TrainerDTO trainerDTO = serializer.getTrainerDTOFromTrainer(trainerDB);
            if (trainerDTO != null) {
                trainerList.add(trainerDTO);
            }
        });
        return trainerList;
    }
}
