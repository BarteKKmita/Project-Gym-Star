package com.learning.gym.star.training.power.service;

import com.learning.gym.star.training.power.PowerTrainingDB;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        repository.updateTrainingCounter(String.valueOf(powerId));
    }

    public void resetPowerStatistics(int powerId){
        repository.resetPowerStatistics(String.valueOf(powerId));
    }

    public String createNewPowerStatistics(){
        return repository.saveAndFlush(new PowerTrainingDB()).getPowerId();
    }
}
