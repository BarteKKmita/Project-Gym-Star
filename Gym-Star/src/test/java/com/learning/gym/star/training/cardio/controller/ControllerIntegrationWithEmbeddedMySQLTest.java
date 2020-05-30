package com.learning.gym.star.training.cardio.controller;


import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerIntegrationWithEmbeddedMySQLTest extends ControllerWithMySQLTestContextSpecification {

    @Test
    public void shouldNotBeNullWhenAutowired(){
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldIncrementTrainingCount() throws Exception{
        //Given
        int cardioId = 1;
        String expectedRequestBody = "3";
        //When
        mockMvc.perform(put("/api/cardio/train/" + cardioId));
        //Then
        mockMvc.perform(get("/api/cardio/" + cardioId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedRequestBody));
    }
}
