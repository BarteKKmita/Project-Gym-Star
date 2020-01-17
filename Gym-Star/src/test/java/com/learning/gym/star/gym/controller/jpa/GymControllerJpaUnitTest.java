package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymFrame;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GymControllerJpaUnitTest {

    private GymFrame testGymFrame = GymFrame.builder()
            .gymName("TestGym")
            .buildingNumber("100")
            .street("Krakowska")
            .city("Kraków")
            .build();
    private GymFrame testGymFrameWithId = GymFrame.builder()
            .gymName("TestGym")
            .buildingNumber("100")
            .street("Krakowska")
            .city("Kraków")
            .gymId("1")
            .build();

    @InjectMocks
    GymControllerJpa controller;
    @Mock
    GymServiceJpa gymService;

    @Test
    public void shouldAddGym(){
        //Given
        String gymID = "1";
        String message = "Your gym id: 1";
        int httpCreated = 201;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.addGym(any(GymFrame.class))).thenReturn(gymID);
        //When
        ResponseEntity responseEntity = controller.addGym(testGymFrame);
        //Then
        assertEquals(httpCreated, responseEntity.getStatusCodeValue());
        assertEquals(message, responseEntity.getBody());
    }

    @Test
    public void shouldReturnConflictWhenAddingExistingGym(){
        //Given
        int httpStatusConflict = 409;
        String message = "Specified gym id already exists ";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.addGym(any(GymFrame.class))).thenReturn("");
        //When
        ResponseEntity responseEntity = controller.addGym(testGymFrame);
        //Then
        assertEquals(httpStatusConflict, responseEntity.getStatusCodeValue());
        assertEquals(message, responseEntity.getBody());
    }

    @Test
    public void shouldGetAllGyms(){
        //Given
        int httpStatusOk = 200;
        int gymListSize = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.getAllGyms()).thenReturn(getAllGymsDummy());
        //When
        ResponseEntity responseEntity = controller.getAllGyms();
        List<GymFrame> gymList = (List<GymFrame>) responseEntity.getBody();
        //Then
        assertEquals(httpStatusOk, responseEntity.getStatusCodeValue());
        assertEquals(gymListSize, gymList.size());
    }


    @Test
    public void shouldGetGymById(){
        //Given
        int httpStatusOk = 200;
        int gymId = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.getGymById(any(Integer.class))).thenReturn(testGymFrameWithId);
        //When
        ResponseEntity responseEntity = controller.getGymById(gymId);
        GymFrame outputGym = (GymFrame) responseEntity.getBody();
        //Then
        assertEquals(httpStatusOk, responseEntity.getStatusCodeValue());
        assertEquals(testGymFrameWithId, outputGym);
    }

    @Test
    public void shouldReturnNotFoundStatusWhenGettingNotExistingGym(){
        //Given
        int httpStatusNotFound = 404;
        int gymId = 2;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.getGymById(any(Integer.class))).thenReturn(null);
        //When
        ResponseEntity responseEntity = controller.getGymById(gymId);
        //Then
        assertEquals(httpStatusNotFound, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getHeaders().isEmpty());
    }

    private List<GymFrame> getAllGymsDummy(){
        List<GymFrame> gymList = new ArrayList<>();
        gymList.add(testGymFrameWithId);
        return gymList;
    }
}