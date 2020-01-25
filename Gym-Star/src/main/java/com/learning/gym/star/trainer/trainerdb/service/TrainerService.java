package com.learning.gym.star.trainer.trainerdb.service;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.database.TrainerRepository;

public class TrainerService {

    private TrainerRepository repository;

    public TrainerService(TrainerRepository repository){
        this.repository = repository;
    }

    public void addTrainer(TrainerDTO){
        repository.saveAndFlush()
    }
}
