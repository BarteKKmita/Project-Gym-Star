package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StatisticsController.class)
public class StatisticsControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService service;

    @Test
    public void shouldNotBeNull(){
        assertNotNull(mockMvc);
        assertNotNull(service);
    }

    @Test
    public void shouldReturnStatusNotFound() throws Exception{
        //Given
        when(service.readStatisticsById(any(String.class))).thenThrow(new NoSuchElementException());
        //Then
        mockMvc.perform(get("/api/statistics/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnExceptionMessageInResponse() throws Exception{
        //Given
        var message = "Test response message";
        when(service.readStatisticsById(any(String.class))).thenThrow(new NoSuchElementException("Test response message"));
        //Then
        mockMvc.perform(get("/api/statistics/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(message));
    }

    @Test
    public void shouldReturnEmptyListAndStatusOk() throws Exception{
        //Given
        var emptyListBracket = "[]";
        when(service.readAllStatistics()).thenReturn(Collections.EMPTY_LIST);
        //When
        MockHttpServletResponse mvcResponse = mockMvc.perform(get("/api/statistics/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        //Then
        assertEquals(emptyListBracket, mvcResponse.getContentAsString());
    }
}
