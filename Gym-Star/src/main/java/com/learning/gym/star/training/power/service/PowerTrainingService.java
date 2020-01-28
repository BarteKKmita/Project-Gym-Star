package com.learning.gym.star.training.power.service;

import com.learning.gym.star.training.power.PowerTrainingDB;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("power training service")
public class PowerTrainingService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PowerTrainingRepository repository;

    public PowerTrainingService(PowerTrainingRepository repository){
        this.repository = repository;
    }

    public int getPowerTrainingCount(int powerTrainingId){
        logger.debug("Attempting to get power training count for power id: {}", powerTrainingId);
        PowerTrainingDB powerTrainingDB = repository.findById(String.valueOf(powerTrainingId)).orElseThrow();
        return powerTrainingDB.getTrainingCount();
    }

    public void doPowerTraining(int powerTrainingId){
        logger.debug("Attempting to increment power training count for power id: {}", powerTrainingId);
        if (repository.existsById(String.valueOf(powerTrainingId))) {
            repository.updateTrainingCounter(String.valueOf(powerTrainingId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public void resetPowerStatistics(int powerTrainingId){
        logger.debug("Attempting to reset power training count for power id: {}", powerTrainingId);
        if (repository.existsById(String.valueOf(powerTrainingId))) {
            repository.resetPowerStatistics(String.valueOf(powerTrainingId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public String createNewPowerStatistics(){
        logger.debug("Saving new power training");
        return repository.saveAndFlush(new PowerTrainingDB()).getPowerId();
    }
}
