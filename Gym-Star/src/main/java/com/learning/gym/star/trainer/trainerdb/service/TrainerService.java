package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("trainer service")
public final class TrainerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);
    private final TrainerRepository repository;
    private final TrainerSerializer serializer;

    public TrainerService(TrainerRepository repository, TrainerSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addTrainer(TrainerDTO trainer){
        LOGGER.debug("Adding trainer to database. {}", trainer);
        repository.saveAndFlush(serializer.getTrainerFromTrainerDTO(trainer));
    }

    public List<TrainerDTO> getAllTrainers(){
        List<TrainerDTO> trainerList = new ArrayList<>();
        repository.findAll().forEach(trainerDB -> {
            var trainerDTO = serializer.getTrainerDTOFromTrainer(trainerDB);
            if (trainerDTO != null) {
                trainerList.add(trainerDTO);
            }
        });
        return trainerList;
    }
}
