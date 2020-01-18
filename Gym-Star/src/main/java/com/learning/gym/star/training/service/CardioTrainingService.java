package com.learning.gym.star.training.service;

import com.learning.gym.star.training.CardioTrainingDB;
import com.learning.gym.star.training.database.CardioTrainingJpaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CardioTrainingService")
@NoArgsConstructor
public class CardioTrainingService {
    @Autowired
    CardioTrainingJpaRepository repository;

    public CardioTrainingService(CardioTrainingJpaRepository repository){
        this.repository = repository;
    }

    public int getCardioTrainingCount(int cardioId){
        CardioTrainingDB training = repository.findById(String.valueOf(cardioId)).orElseThrow();
        return training.getTrainingCount();
    }

    public void doCardioTraining(int cardioId){
        repository.updateTrainingCounter(String.valueOf(cardioId));
    }

    public void resetCardioStatistics(int cardioId){
        repository.resetCardioStatistics(String.valueOf(cardioId));
    }
}
