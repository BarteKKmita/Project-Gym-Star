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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PowerTrainingController.class)
public class PowerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PowerTrainingService service;

    @Test
    public void shouldReturnStatusOKUpdating() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(put("/api/power/train/" + powerId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFound() throws Exception{
        //Given
        String powerId = "1";
        doThrow(new NoSuchElementException()).when(service).doPowerTraining(any(String.class));
        //Then
        mockMvc.perform(put("/api/power/train/" + powerId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatusOKWhenResetting() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(put("/api/power/reset/" + powerId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusOKWhenCreating() throws Exception{
        //Then
        mockMvc.perform(post("/api/power"))
                .andExpect(status().isCreated());
    }
}
