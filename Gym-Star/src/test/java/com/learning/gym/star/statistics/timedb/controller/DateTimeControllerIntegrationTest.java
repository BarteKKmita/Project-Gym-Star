package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.statistics.timedb.service.DateTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DateTimeController.class)
public class DateTimeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DateTimeService service;

    @Test
    public void shouldReturnStatusOk() throws Exception{
        //Given
        int sportsmanStatsId = 1;
        when(service.getSportsManDateAndTimeStatistics(any(String.class))).thenReturn(Collections.emptyList());
        //Then
        mockMvc.perform(get("/api/datetime/" + sportsmanStatsId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFound() throws Exception{
        //Given
        int notExistingStatsId = 2;
        var message = "Test response message";
        when(service.getSportsManDateAndTimeStatistics(any(String.class))).thenThrow(new NoSuchElementException(message));
        //Then
        mockMvc.perform(get("/api/datetime/" + notExistingStatsId))
                .andExpect(status().isNotFound())
                .andExpect(content().string(message));
    }

    @Test
    public void shouldReturnStatusCreated() throws Exception{
        //Given
        int sportsmanStatsId = 1;
        //Then
        mockMvc.perform(post("/api/datetime/" + sportsmanStatsId))
                .andExpect(status().isCreated());
    }
}
