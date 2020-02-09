package com.learning.gym.star.training.cardio.controller;


import com.learning.gym.star.training.cardio.service.CardioTrainingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardioTrainingControllerUnitTest {

    @InjectMocks
    private CardioTrainingController controller;
    @Mock
    private CardioTrainingService service;

    @Test
    public void shouldReturnTrainingCountWhenCallingGetCardioTrainingCount(){
        //Given
        int expectedTrainingCount = 1;
        int cardioId = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(service.getCardioTrainingCount(any(Integer.class))).thenReturn(expectedTrainingCount);
        //When
        ResponseEntity responseEntity = controller.getCardioTrainingCount(cardioId);
        //Then
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(expectedTrainingCount, responseEntity.getBody());
    }

    @Test
    public void shouldReturnResponseBodyWhenCreatingNewCardioStats(){
        //Given
        String cardioId = "1";
        String responseBody = "Your gym id nr : " + cardioId;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        doReturn(cardioId).when(service).createNewCardioStatistics();
        //When
        ResponseEntity responseEntity = controller.createNewCardioStatistics();
        //Then
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertEquals(responseBody, responseEntity.getBody());
    }
}