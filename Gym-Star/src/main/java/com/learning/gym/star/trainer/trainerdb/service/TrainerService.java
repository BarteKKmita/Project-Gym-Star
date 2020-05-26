package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerSerializer;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service("TrainerService")
public class TrainerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);
    private final TrainerRepository repository;
    private final TrainerSerializer serializer;

    public TrainerService(TrainerRepository repository, TrainerSerializer serializer){
        this.repository = repository;
        this.serializer = serializer;
    }

    public void addTrainer(TrainerDTO trainer) throws IllegalArgumentException{
        LOGGER.debug("Adding trainer to database. {}", trainer);
        TrainerEntity trainerEntity = serializer.getTrainerFromTrainerDTO(trainer);
        if (repository.existsById(trainerEntity.getPesel())) {
            throw new EntityExistsException("Trainer with specified pesel number already exists");
        }
        repository.saveAndFlush(trainerEntity);
    }

    public List<TrainerDTO> getAllTrainers() throws IllegalArgumentException{
        return repository.findAll().stream()
                .map(serializer::getTrainerDTOFromTrainer)
                .collect(Collectors.toList());
    }
}