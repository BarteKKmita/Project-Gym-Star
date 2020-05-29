package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SportsmanControllerUnitTest {

    @InjectMocks
    private SportsmanController controller;

    @Mock
    private SportsmanService service;

    @Test
    public void shouldReturnStatusOK(){
        //Given
        String pesel = "123456789";
        when(service.getSportsmanStatistics(pesel)).thenReturn(Collections.emptyList());
        //When
        ResponseEntity sportsmanDateAndTimeStats = controller.getSportsmanDateAndTimeStats(pesel);
        //Then
        assertEquals(HttpStatus.OK, sportsmanDateAndTimeStats.getStatusCode());
    }

    @Test
    public void shouldReturnStatusOKWhenGettingTrainer(){
        String pesel = "123456789";
        when(service.getMyTrainerData(pesel)).thenReturn(mock(TrainerDTO.class));
        //When
        ResponseEntity myTrainer = controller.getMyTrainer(pesel);
        //Then
        assertEquals(HttpStatus.OK, myTrainer.getStatusCode());
    }
}