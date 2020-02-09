package com.learning.gym.star.training.cardio.controller;

import com.learning.gym.star.training.cardio.service.CardioTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CardioTrainingController.class)
public class CardioControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardioTrainingService service;

    @Test
    public void shouldListenToControllerEndpoint() throws Exception{
        //Given
        int cardioId = 1;
        //Then
        mockMvc.perform(get("/api/cardio/" + cardioId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotListenToOtherControllersEndpoint() throws Exception{
        //Given
        int powerId = 1;
        //Then
        mockMvc.perform(get("/api/power/" + powerId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatusOKWhenUpdatingExistingCardioStats() throws Exception{
        //Given
        int cardioId = 1;
        //Then
        mockMvc.perform(put("/api/cardio/train/" + cardioId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFountWhenUpdatingNotExistingCardioStats() throws Exception{
        //Given
        int cardioId = 1;
        doThrow(new NoSuchElementException()).when(service).doCardioTraining(any(Integer.class));
        //Then
        mockMvc.perform(put("/api/cardio/train/" + cardioId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatusOKWhenResettingCardioStats() throws Exception{
        //Given
        int cardioId = 1;
        //Then
        mockMvc.perform(put("/api/cardio/reset/" + cardioId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusOKWhenCreatingNewCardioStats() throws Exception{
        //Then
        mockMvc.perform(post("/api/cardio/create/"))
                .andExpect(status().isCreated());
    }
}
