package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.EmbeddedMySqlProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StatisticsControllerIntegrationEmbeddedMySQLTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUpClass(){
        EmbeddedMySqlProvider.setUpClass();
    }

    @AfterAll
    public static void tearDownClass(){
        EmbeddedMySqlProvider.tearDownClass();
    }

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
        //Then
        mockMvc.perform(get("/api/statistics/" + existingStatsId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statisticsId").value(1))
                .andExpect(jsonPath("$.cardioTrainingEntity.cardioId").value(1))
                .andExpect(jsonPath("$.cardioTrainingEntity.trainingCount").value(2))
                .andExpect(jsonPath("$.powerTrainingEntity.powerId").value(1))
                .andExpect(jsonPath("$.powerTrainingEntity.trainingCount").value(5));
    }
}