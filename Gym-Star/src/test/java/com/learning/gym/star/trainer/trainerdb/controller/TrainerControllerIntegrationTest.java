package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.trainer.trainerdb.TrainerDTO;
import com.learning.gym.star.trainer.trainerdb.service.TrainerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityExistsException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainerController.class)
class TrainerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService service;

    private static final String URL = "/api/trainer";

    @Test
    public void shouldReturnStatusCreated() throws Exception{
        //Given
        var testTrainer = getValidTrainer();
        //Then
        mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(testTrainer))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnMessageWhenShortSurname() throws Exception{
        //Given
        var expectedMessage = "Surname is too short";
        var testTrainer = addTrainerCost(addTrainerName(addTrainerPesel(new JSONObject())))
                .put("surname", "B")
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(testTrainer))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedMessage));
    }

    @Test
    public void shouldReturnMessageWhenNoSurname() throws Exception{
        //Given
        var expectedMessage = "Trainer has to have surname";
        var testTrainer = addTrainerCost(addTrainerName(addTrainerPesel(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(testTrainer))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedMessage));
    }

    @Test
    public void shouldReturnMessageWhenCostIsNull() throws Exception{
        //Given
        var expectedMessage = "Please specify cost per hour of training";
        var testTrainer = addTrainerName(addTrainerName(addTrainerPesel(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(testTrainer))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedMessage));
    }

    @Test
    public void shouldReturnMessageWhenPeselIsNull() throws Exception{
        //Given
        var expectedMessage = "Please enter pesel number.";
        var testTrainer = addTrainerName(addTrainerName(addTrainerCost(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(testTrainer))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedMessage));
    }

    @Test
    public void shouldReturnMessageWhenPeselInvalid() throws Exception{
        //Given
        var invalidPeselNumber = 123456790;
        var expectedMessage = "Invalid Pesel number.";
        var testTrainer = addTrainerName(addTrainerName(addTrainerCost(new JSONObject())))
                .put("pesel", invalidPeselNumber)
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON)
                .content(testTrainer))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedMessage));
    }

    @Test
    public void shouldReturnStatusConflict() throws Exception{
        //Given
        var testTrainer = getValidTrainer();
        doThrow(EntityExistsException.class).when(service).addTrainer(any(TrainerDTO.class));
        //Then
        mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON)
                .content(testTrainer))
                .andExpect(status().isConflict());
    }

    private String getValidTrainer() throws JSONException{
        return addTrainerName(addTrainerSurname(addTrainerCost(addTrainerPesel(new JSONObject())))).toString();
    }

    private JSONObject addTrainerPesel(JSONObject trainer) throws JSONException{
        var validPesel = "99100877254";
        return trainer.put("pesel", validPesel);
    }

    private JSONObject addTrainerName(JSONObject trainer) throws JSONException{
        return trainer.put("name", "Joe");
    }

    private JSONObject addTrainerSurname(JSONObject trainer) throws JSONException{
        return trainer.put("surname", "Ordinary");
    }


    private JSONObject addTrainerCost(JSONObject trainer) throws JSONException{
        var moneyAmount = 30;
        return trainer.put("cost", moneyAmount);
    }
}