package com.learning.gym.star.training.power.controller;

import com.learning.gym.star.training.power.service.PowerTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PowerTrainingController.class)
public class PowerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PowerTrainingService service;

    @Test
    public void shouldListenToControllerEndpoint() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(get("/api/power/" + powerId)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotListenToOtherControllersEndpoint() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(get("/api/cardio/" + powerId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatusOKWhenUpdatingExistingPowerStats() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(put("/api/power/train/" + powerId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFountWhenUpdatingNotExistingPowerStats() throws Exception{
        //Given
        int powerId = 1;
        doThrow(new NoSuchElementException()).when(service).doPowerTraining(any(Integer.class));
        //Then
        mockMvc.perform(put("/api/power/train/" + powerId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatusOKWhenResettingPowerStats() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(put("/api/power/reset/" + powerId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusOKWhenCreatingNewPowerStats() throws Exception{
        //Then
        mockMvc.perform(post("/api/power/create/"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnResponseBodyWhenCreatingNewPowerStats() throws Exception{
        //Given
        String powerId = "1";
        String responseBody = "Your gym id nr : " + powerId;
        when(service.createNewPowerStatistics()).thenReturn(powerId);
        //Then
        mockMvc.perform(post("/api/power/create/")
                .contentType("application/json")
                .content(responseBody))
                .andExpect(status().isCreated());
    }
}
