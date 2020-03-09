package com.learning.gym.star.training.power.service;

import com.learning.gym.star.training.power.PowerTrainingEntity;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("PowerTrainingService")
public class PowerTrainingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PowerTrainingService.class);
    private final PowerTrainingRepository repository;

    public PowerTrainingService(PowerTrainingRepository repository){
        this.repository = repository;
    }

    public int getPowerTrainingCount(String powerTrainingId){
        LOGGER.info("Attempting to get power training count for power id: {}", powerTrainingId);
        PowerTrainingEntity powerTraining = repository.findById(powerTrainingId)
                .orElseThrow(() -> handleNoStatisticsFound(powerTrainingId));
        return powerTraining.getTrainingCount();
    }

    public void doPowerTraining(String powerTrainingId){
        LOGGER.info("Attempting to increment power training count for power id: {}", powerTrainingId);
        if (repository.existsById(powerTrainingId)) {
            repository.updateTrainingCounter(powerTrainingId);
        } else {
            handleNoStatisticsFound(powerTrainingId);
        }
    }

    public void resetPowerStatistics(String powerTrainingId){
        LOGGER.info("Attempting to reset power training count for power id: {}", powerTrainingId);
        if (repository.existsById(powerTrainingId)) {
            repository.resetPowerStatistics(powerTrainingId);
        } else {
            handleNoStatisticsFound(powerTrainingId);
        }
    }

    public String createNewPowerStatistics(){
        LOGGER.info("Saving new power training");
        return repository.saveAndFlush(new PowerTrainingEntity()).getPowerId();
    }

    private NoSuchElementException handleNoStatisticsFound(String powerTrainingId){
        LOGGER.info("There is no power statistics with id: {}", powerTrainingId);
        throw new NoSuchElementException("There is no power statistics with id: " + powerTrainingId);
    }
}
