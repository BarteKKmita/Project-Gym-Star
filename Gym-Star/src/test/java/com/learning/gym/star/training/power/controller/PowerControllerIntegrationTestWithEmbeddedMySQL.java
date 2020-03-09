package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import com.learning.gym.star.training.power.service.PowerTrainingService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PowerControllerIntegrationTestWithEmbeddedMySQL {
    @Autowired
    private PowerTrainingController controllerJpa;
    @Autowired
    private PowerTrainingService service;
    @Autowired
    private PowerTrainingRepository repository;
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
    public void shouldReturnTrainingCount() throws Exception{
        //Given
        int powerId = 1;
        String expectedRequestBody = "5";
        mockMvc.perform(get("/api/power/" + powerId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedRequestBody));
    }
}
