package com.learning.gym.star.training.power.service;


import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PowerTrainingServiceUnitTest {

    @InjectMocks
    private PowerTrainingService service;

    @Mock
    private PowerTrainingRepository repository;

    @Test
    public void shouldThrowExceptionWhenGetting(){
        //Given
        String powerId = "1";
        //Then
        assertThrows(NoSuchElementException.class, () -> service.getPowerTrainingCount(powerId));
    }

    @Test
    public void shouldThrowExceptionWhenUpdating(){
        //Given
        String powerId = "1";
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.doPowerTraining(powerId));
    }

    @Test
    public void shouldThrowExceptionWhenResetting(){
        //Given
        String powerId = "1";
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.resetPowerStatistics(powerId));
    }
}