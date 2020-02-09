package com.learning.gym.star.training.cardio.service;

import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("CardioTrainingService")
@NoArgsConstructor
public class CardioTrainingService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CardioTrainingJpaRepository repository;

    @Autowired
    public CardioTrainingService(CardioTrainingJpaRepository repository){
        this.repository = repository;
    }

    public int getCardioTrainingCount(int cardioId){
        logger.debug("Attempting to get cardio training count for cardio id: {}", cardioId);
        CardioTrainingDB training = repository.findById(String.valueOf(cardioId)).orElseThrow();
        return training.getTrainingCount();
    }

    public void doCardioTraining(int cardioId){
        logger.debug("Attempting to increment cardio training count for cardio id: {}", cardioId);
        if (repository.existsById(String.valueOf(cardioId))) {
            repository.updateTrainingCounter(String.valueOf(cardioId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public void resetCardioStatistics(int cardioId){
        logger.debug("Attempting to reset cardio training count for cardio id: {}", cardioId);
        if (repository.existsById(String.valueOf(cardioId))) {
            repository.resetCardioStatistics(String.valueOf(cardioId));
        } else {
            throw new NoSuchElementException();
        }
    }

    public String createNewCardioStatistics(){
        logger.debug("Saving new cardio training");
        return repository.saveAndFlush(new CardioTrainingDB()).getCardioId();
    }
}
