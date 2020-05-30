package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DateTimeControllerIntegrationWithEmbeddedMySQLTest extends ControllerWithMySQLTestContextSpecification {

    @Test
    public void shouldControllerNotBeNullWhenAutowired(){
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldReturnSportsmanDateTimeStats() throws Exception{
        //Given
        int sportsmanStatsId = 1;
        //Then
        mockMvc.perform(get("/api/datetime/" + sportsmanStatsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].statisticsId").value("1"));
    }
}
