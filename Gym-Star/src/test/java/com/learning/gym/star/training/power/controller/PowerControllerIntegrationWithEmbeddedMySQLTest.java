package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PowerControllerIntegrationWithEmbeddedMySQLTest extends ControllerWithMySQLTestContextSpecification {

    @Test
    public void shouldControllerNotBeNullWhenAutowired(){
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
