package com.learning.gym.star.training.cardio.service;

import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CardioTrainingService")
@NoArgsConstructor
public class CardioTrainingService {
    @Autowired
    private CardioTrainingJpaRepository repository;

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

    public String createNewCardioStatistics(){
        return repository.saveAndFlush(new CardioTrainingDB()).getCardioId();
    }
}
