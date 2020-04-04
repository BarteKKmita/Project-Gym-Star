package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.controller.GymDTO;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GymControllerJpaUnitTest {

    private GymDTO testGymDTO = getTestGymWithId(Optional.empty());
    private GymDTO testGymDTOWithId = getTestGymWithId(Optional.of("1"));

    @InjectMocks
    private GymControllerJpa controller;
    @Mock
    private GymServiceJpa gymService;

    @Test
    public void shouldAddGym(){
        //Given
        String gymID = "1";
        String message = "Your gym id: 1";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.addGym(any(GymDTO.class))).thenReturn(gymID);
        //When
        ResponseEntity responseEntity = controller.addGym(testGymDTO);
        //Then
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        assertEquals(message, responseEntity.getBody());
    }

    @Test
    public void shouldReturnConflictWhenAddingExistingGym(){
        //Given
        String message = "Gym data was provided with gym id. It should be empty since app generates id automatically";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.addGym(any(GymDTO.class))).thenReturn("");
        //When
        ResponseEntity responseEntity = controller.addGym(testGymDTO);
        //Then
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getStatusCodeValue());
        assertEquals(message, responseEntity.getBody());
    }

    @Test
    public void shouldGetAllGyms(){
        //Given
        int gymListSize = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.getAllGyms()).thenReturn(getAllGymsDummy());
        //When
        ResponseEntity responseEntity = controller.getAllGyms();
        List<GymDTO> gymList = (List<GymDTO>) responseEntity.getBody();
        //Then
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(gymListSize, gymList.size());
    }

    @Test
    public void shouldGetGymById(){
        //Given
        int gymId = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(gymService.getGymById(any(Integer.class))).thenReturn(testGymDTOWithId);
        //When
        ResponseEntity responseEntity = controller.getGymById(gymId);
        GymDTO outputGym = (GymDTO) responseEntity.getBody();
        //Then
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(testGymDTOWithId, outputGym);
    }

    private List<GymDTO> getAllGymsDummy(){
        List<GymDTO> gymList = new ArrayList<>();
        gymList.add(testGymDTOWithId);
        return gymList;
    }

    private GymDTO getTestGymWithId(Optional<String> gymId){
        return GymDTO.builder()
                .gymName("TestGym")
                .buildingNumber("100")
                .street("Krakowska")
                .city("Kraków")
                .gymId(gymId.orElse(null))
                .build();
    }
}