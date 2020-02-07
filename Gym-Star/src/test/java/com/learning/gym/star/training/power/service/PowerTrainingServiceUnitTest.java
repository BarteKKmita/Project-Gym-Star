package com.learning.gym.star.training.power.service;


import com.learning.gym.star.training.power.PowerTrainingDB;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PowerTrainingServiceUnitTest {
    private Optional<PowerTrainingDB> testPowerTraining = Optional.of(mock(PowerTrainingDB.class));

    @InjectMocks
    private PowerTrainingService service;

    @Mock
    private PowerTrainingRepository repository;

    @Test
    public void getPowerTrainingCount(){
        //Given
        int powerId = 1;
        int trainingCount = 6;
        when(repository.findById(any(String.class))).thenReturn(testPowerTraining);
        when(testPowerTraining.get().getTrainingCount()).thenReturn(trainingCount);
        //When
        int trainingCountOutput = service.getPowerTrainingCount(powerId);
        //Then
        assertEquals(trainingCount, trainingCountOutput);
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenGettingNotExistingRecord(){
        //Given
        int powerId = 1;
        //Then
        assertThrows(NoSuchElementException.class, () -> service.getPowerTrainingCount(powerId));
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenUpdatingNotExistingRecord(){
        //Given
        int powerId = 1;
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.doPowerTraining(powerId));
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenResettingNotExistingRecord(){
        //Given
        int powerId = 1;
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.resetPowerStatistics(powerId));
    }

    @Test
    public void createNewPowerStatistics(){
        //Given
        String expectedPowerId = "1";
        PowerTrainingDB powerTrainingDB = mock(PowerTrainingDB.class);
        when(repository.saveAndFlush(any(PowerTrainingDB.class))).thenReturn(powerTrainingDB);
        when(powerTrainingDB.getPowerId()).thenReturn(expectedPowerId);
        //When
        String powerId = service.createNewPowerStatistics();
        //Then
        assertEquals(expectedPowerId, powerId);
    }
}