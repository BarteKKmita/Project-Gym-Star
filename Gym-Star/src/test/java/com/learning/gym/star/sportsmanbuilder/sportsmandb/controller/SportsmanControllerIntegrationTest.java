package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.service.SportsmanService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SportsmanController.class)
class SportsmanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportsmanService service;

    private static final String URL = "/api/sportsman/";

    @Test
    public void shouldReturnStatusCreated() throws Exception{
        //Given
        var validSportsman = getValidSportsman();
        //Then
        mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(validSportsman))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnMessageWhenInvalidPesel() throws Exception{
        //Given
        var message = "Pesel must have proper number of digits and has to be valid";
        var sportsman = getSportsmanSurname(getSportsmanGender(getSportsmanName(new JSONObject())))
                .put("sportsmanPesel", "123456789")
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenEmptyPesel() throws Exception{
        //Given
        var message = "Please enter pesel number.";
        var sportsman = getSportsmanSurname(getSportsmanGender(getSportsmanName(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenWrongGender() throws Exception{
        //Given
        var message = "Entered invalid gender: K. Valid gender is M or F.";
        var sportsman = getSportsmanSurname(getSportsmanPesel(getSportsmanName(new JSONObject())))
                .put("gender", "K")
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isNotAcceptable())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenEmptyGender() throws Exception{
        //Given
        var message = "Inserted wrong gender character. Gender can be M or F.";
        var sportsman = getSportsmanSurname(getSportsmanPesel(getSportsmanName(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenEmptyName() throws Exception{
        //Given
        var message = "Sportsman has to have name";
        var sportsman = getSportsmanSurname(getSportsmanPesel(getSportsmanGender(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenEmptySurname() throws Exception{
        //Given
        var message = "Sportsman has to have surname";
        var sportsman = getSportsmanName(getSportsmanPesel(getSportsmanGender(new JSONObject())))
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenShortName() throws Exception{
        //Given
        var message = "Name is too short";
        var sportsman = getSportsmanSurname(getSportsmanPesel(getSportsmanGender(new JSONObject())))
                .put("name", "A")
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenShortSurname() throws Exception{
        //Given
        var message = "Surname is too short";
        var sportsman = getSportsmanName(getSportsmanPesel(getSportsmanGender(new JSONObject())))
                .put("surname", "A")
                .toString();
        //Then
        MvcResult mvcResult = mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(sportsman))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    @Test
    public void shouldReturnMessageWhenWrongPeselInPathVariable() throws Exception{
        //Given
        var message = "Pesel must have proper number of digits and has to be valid";
        var sportsmanPesel = "123456789";
        //Then
        MvcResult mvcResult = mockMvc.perform(get(URL + sportsmanPesel + "/date-time-stats"))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    public void shouldReturnMessageWhenWrongPeselInRequestBody() throws Exception{
        //Given
        var message = "Pesel must have 11 digits and has to be valid";
        var sportsmanPesel = "67092924133";
        var trainerPesel = "123456789";
        //Then
        MvcResult mvcResult = mockMvc.perform(get(URL + sportsmanPesel + "/trainer")
                .contentType(APPLICATION_JSON_VALUE)
                .content(trainerPesel))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(message));
    }

    public void shouldReturnStatusCreatedWhenChoosingTrainer() throws Exception{
        //Given
        var sportsmanPesel = "93062687511";
        var trainerPesel = "63073086337";
        //Then
        mockMvc.perform(put(URL + sportsmanPesel + "/trainer")
                .contentType(APPLICATION_JSON_VALUE)
                .content(trainerPesel))
                .andExpect(status().isCreated());
    }

    public void shouldReturnStatusOKWhenTrainingCardio() throws Exception{
        //Given
        var sportsmanPesel = "93062687511";
        //Then
        mockMvc.perform(put(URL + sportsmanPesel + "/trainer"))
                .andExpect(status().isOk());
    }

    private String getValidSportsman() throws JSONException{
        return getSportsmanName(getSportsmanSurname(getSportsmanPesel(getSportsmanGender(new JSONObject())))).toString();
    }

    private JSONObject getSportsmanName(JSONObject sportsman) throws JSONException{
        sportsman.put("name", "Joe");
        return sportsman;
    }

    private JSONObject getSportsmanSurname(JSONObject sportsman) throws JSONException{
        sportsman.put("surname", "Ordinary");
        return sportsman;
    }

    private JSONObject getSportsmanPesel(JSONObject sportsman) throws JSONException{
        sportsman.put("sportsmanPesel", "94041996411");
        return sportsman;
    }

    private JSONObject getSportsmanGender(JSONObject sportsman) throws JSONException{
        sportsman.put("gender", "M");
        return sportsman;
    }
}