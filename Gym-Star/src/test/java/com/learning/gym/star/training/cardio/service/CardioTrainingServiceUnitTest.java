package com.learning.gym.star.training.cardio.service;


import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
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
public class CardioTrainingServiceUnitTest {
    private Optional<CardioTrainingDB> testCardioTraining = Optional.of(mock(CardioTrainingDB.class));

    @InjectMocks
    private CardioTrainingService service;

    @Mock
    private CardioTrainingJpaRepository repository;

    @Test
    public void getCardioTrainingCount(){
        //Given
        int cardioId = 1;
        int trainingCount = 6;
        when(repository.findById(any(String.class))).thenReturn(testCardioTraining);
        when(testCardioTraining.get().getTrainingCount()).thenReturn(trainingCount);
        //When
        int trainingCountOutput = service.getCardioTrainingCount(cardioId);
        //Then
        assertEquals(trainingCount, trainingCountOutput);
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenGettingNotExistingRecord(){
        //Given
        int cardioId = 1;
        //Then
        assertThrows(NoSuchElementException.class, () -> service.getCardioTrainingCount(cardioId));
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenUpdatingNotExistingRecord(){
        //Given
        int cardioId = 1;
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.doCardioTraining(cardioId));
    }

    @Test
    public void shouldThrowNotSuchElementExceptionWhenResettingNotExistingRecord(){
        //Given
        int cardioId = 1;
        when(repository.existsById(any(String.class))).thenReturn(false);
        //Then
        assertThrows(NoSuchElementException.class, () -> service.resetCardioStatistics(cardioId));
    }

    @Test
    public void createNewCardioStatistics(){
        //Given
        String expectedCardioId = "1";
        CardioTrainingDB cardioTrainingDB = mock(CardioTrainingDB.class);
        when(repository.saveAndFlush(any(CardioTrainingDB.class))).thenReturn(cardioTrainingDB);
        when(cardioTrainingDB.getCardioId()).thenReturn(expectedCardioId);
        //When
        String cardioId = service.createNewCardioStatistics();
        //Then
        assertEquals(expectedCardioId, cardioId);
    }
}