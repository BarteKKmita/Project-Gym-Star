package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.EmbeddedMySqlProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DateTimeControllerIntegrationWithEmbeddedMySQLTest {
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
