package com.learning.gym.star.training.cardio.database;

import com.learning.gym.star.TestRepositoryContextSpecification;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CardioTrainingJpaRepositoryTest extends TestRepositoryContextSpecification {

    @Autowired
    private CardioTrainingJpaRepository repository;

    @Test
    public void shouldNotBeNull(){
        assertNotNull(repository);
    }

    @Test
    public void shouldReturnCardioEntity(){
        //Given
        var trainingId = "1";
        //When
        boolean isCardioEntityExists = repository.findById(trainingId).isPresent();
        //Then
        assertTrue(isCardioEntityExists);
    }

    @Test
    public void shouldUpdateCardioTrainingCounter(){
        //Given
        var trainingId = "1";
        int expectedTrainingCount = 3;
        //When
        repository.updateTrainingCounter(trainingId);
        int actualTrainingCount = repository.findById(trainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldResetCardioTrainingCounter(){
        //Given
        var trainingId = "1";
        int expectedTrainingCount = 0;
        //When
        repository.resetCardioStatistics(trainingId);
        int actualTrainingCount = repository.findById(trainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldCreateCardioTrainingRecord(){
        //Given
        var newCardioId = repository.saveAndFlush(new CardioTrainingEntity()).getCardioId();
        //When
        boolean isCardioStatsCreated = repository.existsById(newCardioId);
        //Then
        assertTrue(isCardioStatsCreated);
    }
}