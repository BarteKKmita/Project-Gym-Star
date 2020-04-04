package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.junit.jupiter.api.BeforeEach;
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
class PowerTrainingControllerTest {
    @InjectMocks
    private PowerTrainingController controller;
    @Mock
    private PowerTrainingService service;

    @BeforeEach
    public void setUp(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void getPowerTrainingCount(){
        //Given
        int expectedTrainingCount = 1;
        int powerId = 1;
        when(service.getPowerTrainingCount(any(String.class))).thenReturn(expectedTrainingCount);
        //When
        ResponseEntity responseEntity = controller.getPowerTrainingCount(powerId);
        //Then
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(expectedTrainingCount, responseEntity.getBody());
    }

    @Test
    public void shouldReturnResponseBodyWhenCreatingNewPowerStats(){
        //Given
        String powerId = "1";
        String responseBody = "Your gym id nr : " + powerId;
        doReturn(powerId).when(service).createNewPowerStatistics();
        //When
        ResponseEntity responseEntity = controller.createNewStatistics();
        //Then
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertEquals(responseBody, responseEntity.getBody());
    }
}