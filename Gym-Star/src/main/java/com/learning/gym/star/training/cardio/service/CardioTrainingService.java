package com.learning.gym.star.training.cardio.service;

import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("CardioTrainingService")
public final class CardioTrainingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardioTrainingService.class);
    private final CardioTrainingJpaRepository repository;

    public CardioTrainingService(CardioTrainingJpaRepository repository){
        this.repository = repository;
    }

    public int getCardioTrainingCount(String cardioId){
        LOGGER.debug("Attempting to get cardio training count for cardio id: {}", cardioId);
        CardioTrainingEntity training = repository.findById(cardioId)
                .orElseThrow(() -> handleNoStatisticsFound(cardioId));
        return training.getTrainingCount();
    }

    public void doCardioTraining(String cardioId){
        LOGGER.debug("Attempting to increment cardio training count for cardio id: {}", cardioId);
        if (repository.existsById(cardioId)) {
            repository.updateTrainingCounter(cardioId);
        } else {
            handleNoStatisticsFound(cardioId);
        }
    }

    public void resetCardioStatistics(String cardioId){
        LOGGER.debug("Attempting to reset cardio training count for cardio id: {}", cardioId);
        if (repository.existsById(cardioId)) {
            repository.resetCardioStatistics(cardioId);
        } else {
            handleNoStatisticsFound(cardioId);
        }
    }

    public String createNewCardioStatistics(){
        LOGGER.debug("Saving new cardio training");
        return repository.saveAndFlush(new CardioTrainingEntity()).getCardioId();
    }

    private NoSuchElementException handleNoStatisticsFound(String cardioId){
        LOGGER.debug("There is no cardio statistics with id: {}", cardioId);
        throw new NoSuchElementException("There is no cardio statistics with id: " + cardioId);
    }
}
