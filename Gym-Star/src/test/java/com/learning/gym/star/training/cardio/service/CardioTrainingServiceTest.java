package com.learning.gym.star.training.cardio.service;


import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardioTrainingServiceTest {
    private CardioTrainingEntity testCardioTraining = mock(CardioTrainingEntity.class);

    @InjectMocks
    private CardioTrainingService service;

    @Mock
    private CardioTrainingJpaRepository repository;

    @Test
    public void shouldThrowExceptionWhenGetting(){
        //Given
        String cardioId = "1";
        //Then
        assertThrows(NoSuchElementException.class, () -> service.getCardioTrainingCount(cardioId));
    }

    @Test
    public void shouldThrowExceptionWhenUpdating(){
        //Given
        String cardioId = "1";
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.doCardioTraining(cardioId));
    }

    @Test
    public void shouldThrowExceptionWhenResetting(){
        //Given
        String cardioId = "1";
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.resetCardioStatistics(cardioId));
    }
}