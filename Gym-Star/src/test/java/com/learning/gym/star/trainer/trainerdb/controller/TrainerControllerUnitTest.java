package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.trainer.trainerdb.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerControllerUnitTest {
    @InjectMocks
    private TrainerController controller;

    @Mock
    private TrainerService service;

    @Test
    public void shouldReturnStatusOK(){
        //Given
        when(service.getAllTrainers()).thenReturn(Collections.emptyList());
        //When
        ResponseEntity allTrainers = controller.getAllTrainers();
        //Then
        assertEquals(HttpStatus.OK, allTrainers.getStatusCode());
    }
}