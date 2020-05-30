package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class GymControllerJpaIntegrationTest extends ControllerWithMySQLTestContextSpecification {

    @Test
    public void shouldGetFirstGymWhenGetGymWithId1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jpa/gym/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gymName").value("pierwsza"));
    }
}
