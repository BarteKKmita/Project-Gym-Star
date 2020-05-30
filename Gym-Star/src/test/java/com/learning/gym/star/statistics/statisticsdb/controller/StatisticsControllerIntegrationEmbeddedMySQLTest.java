package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class StatisticsControllerIntegrationEmbeddedMySQLTest extends ControllerWithMySQLTestContextSpecification {

    @Test
    public void shouldFieldsNotBeNull(){
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldAddStatsRecord() throws Exception{
        //Given
        var expectedStatsId = "2";
        //Then
        mockMvc.perform(post("/api/statistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedStatsId));
    }

    @Test
    public void shouldGetStatsRecord() throws Exception{
        //Given
        var existingStatsId = "1";
        var cardioId = 1;
        var cardioTrainingCount = 2;
        var powerId = 1;
        var powerTrainingCount = 5;
        //Then
        mockMvc.perform(get("/api/statistics/" + existingStatsId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statisticsId").value(1))
                .andExpect(jsonPath("$.cardioTrainingEntity.cardioId").value(cardioId))
                .andExpect(jsonPath("$.cardioTrainingEntity.trainingCount").value(cardioTrainingCount))
                .andExpect(jsonPath("$.powerTrainingEntity.powerId").value(powerId))
                .andExpect(jsonPath("$.powerTrainingEntity.trainingCount").value(powerTrainingCount));
    }
}