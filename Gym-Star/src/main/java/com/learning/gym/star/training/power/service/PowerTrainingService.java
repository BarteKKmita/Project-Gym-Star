package com.learning.gym.star.training.power.service;

import com.learning.gym.star.training.power.PowerTrainingDB;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("power training service")
public class PowerTrainingService {
    @Autowired
    private PowerTrainingRepository repository;

    public PowerTrainingService(PowerTrainingRepository repository){
        this.repository = repository;
    }

    public int getPowerTrainingCount(int powerId){
        PowerTrainingDB powerTrainingDB = repository.findById(String.valueOf(powerId)).orElseThrow();
        return powerTrainingDB.getTrainingCount();
    }

    public void doPowerTraining(int powerId){
        if (repository.existsById(String.valueOf(powerId))) {
            repository.updateTrainingCounter(String.valueOf(powerId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public void resetPowerStatistics(int powerId){
        if (repository.existsById(String.valueOf(powerId))) {
            repository.resetPowerStatistics(String.valueOf(powerId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public String createNewPowerStatistics(){
        return repository.saveAndFlush(new PowerTrainingDB()).getPowerId();
    }
}