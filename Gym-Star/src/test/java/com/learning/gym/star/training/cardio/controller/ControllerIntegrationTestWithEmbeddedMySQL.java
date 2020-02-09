package com.learning.gym.star.training.cardio.controller;


import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import com.learning.gym.star.training.cardio.service.CardioTrainingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerIntegrationTestWithEmbeddedMySQL {
    @Autowired
    private CardioTrainingController controllerJpa;
    @Autowired
    private CardioTrainingService service;
    @Autowired
    private CardioTrainingJpaRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldControllerNotBeNullWhenAutowired(){
        assertNotNull(controllerJpa);
        assertNotNull(repository);
        assertNotNull(service);
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldIncrementTrainingCountWhenDoingCardioTraining() throws Exception{
        //Given
        int cardioId = 1;
        String expectedRequestBody = "1";
        //When
        mockMvc.perform(put("/api/cardio/train/" + cardioId));
        //Then
        mockMvc.perform(get("/api/cardio/" + cardioId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedRequestBody));
    }
}
