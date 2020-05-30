package com.learning.gym.star.training.power.database;

import com.learning.gym.star.TestRepositoryContextSpecification;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class PowerTrainingJpaRepositoryTest extends TestRepositoryContextSpecification {

    @Autowired
    private PowerTrainingRepository repository;

    @Test
    public void shouldNotBeNull(){
        assertNotNull(repository);
    }

    @Test
    public void shouldReturnPowerEntity(){
        //Given
        var powerTrainingId = "1";
        //When
        boolean isPowerTrainingExists = repository.existsById(powerTrainingId);
        //Then
        assertTrue(isPowerTrainingExists);
    }

    @Test
    public void shouldUpdatePowerTrainingCounter(){
        //Given
        var powerTrainingId = "1";
        int expectedTrainingCount = 6;
        //When
        repository.updateTrainingCounter(powerTrainingId);
        int actualTrainingCount = repository.findById(powerTrainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldResetPowerTrainingCounter(){
        //Given
        var powerTrainingId = "1";
        int expectedTrainingCount = 0;
        //When
        repository.resetPowerStatistics(powerTrainingId);
        int actualTrainingCount = repository.findById(powerTrainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldCreatePowerTrainingEntity(){
        //Given
        var newPowerId = repository.saveAndFlush(new PowerTrainingEntity()).getPowerId();
        //When
        boolean isPowerStatsCreated = repository.existsById(newPowerId);
        //Then
        assertTrue(isPowerStatsCreated);
    }
}
