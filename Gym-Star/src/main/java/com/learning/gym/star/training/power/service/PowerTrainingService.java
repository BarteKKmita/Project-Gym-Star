package com.learning.gym.star.training.power.service;

import com.learning.gym.star.training.power.PowerTrainingDB;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("PowerTrainingService")
public final class PowerTrainingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrainingService.class);
    private final PowerTrainingRepository repository;

    public PowerTrainingService(PowerTrainingRepository repository){
        this.repository = repository;
    }

    public int getPowerTrainingCount(String powerTrainingId){
        LOGGER.debug("Attempting to get power training count for power id: {}", powerTrainingId);
        PowerTrainingDB powerTrainingDB = repository.findById(powerTrainingId)
                .orElseThrow(() -> handleNoStatisticsFound(powerTrainingId));
        return powerTrainingDB.getTrainingCount();
    }

    public void doPowerTraining(String powerTrainingId){
        LOGGER.debug("Attempting to increment power training count for power id: {}", powerTrainingId);
        if (repository.existsById(powerTrainingId)) {
            repository.updateTrainingCounter(powerTrainingId);
        } else {
            handleNoStatisticsFound(powerTrainingId);
        }
    }

    public void resetPowerStatistics(String powerTrainingId){
        LOGGER.debug("Attempting to reset power training count for power id: {}", powerTrainingId);
        if (repository.existsById(powerTrainingId)) {
            repository.resetPowerStatistics(powerTrainingId);
        } else {
            handleNoStatisticsFound(powerTrainingId);
        }
    }

    public String createNewPowerStatistics(){
        LOGGER.debug("Saving new power training");
        return repository.saveAndFlush(new PowerTrainingDB()).getPowerId();
    }

    private NoSuchElementException handleNoStatisticsFound(String powerTrainingId){
        LOGGER.debug("There is no power statistics with id: {}", powerTrainingId);
        throw new NoSuchElementException("There is no power statistics with id: " + powerTrainingId);
    }
}
