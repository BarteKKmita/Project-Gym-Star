package com.learning.gym.star.sportsmanbuilder.sportsmandb.controller;

import com.learning.gym.star.EmbeddedMySqlProvider;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SportsmanControllerWithEmbeddedMySQLTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SportsmanRepository repository;

    private static final String URL = "/api/sportsman/";

    @BeforeAll
    public static void setUpClass(){
        EmbeddedMySqlProvider.setUpClass();
    }

    @AfterAll
    public static void tearDownClass(){
        EmbeddedMySqlProvider.tearDownClass();
    }

    @Test
    public void shouldNotBeNullWhenAutowired(){
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldGetAllStats() throws Exception{
        //Given
        MvcResult mvcResult = mockMvc.perform(get(URL + "97112848572/date-time-stats"))
                .andExpect(status().isOk())
                .andReturn();
        //Then
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void shouldChooseTrainer() throws Exception{
        //Given
        var sportsmanPesel = "98080489747";
        var trainerPesel = "82062972549";
        var url = URL + sportsmanPesel + "/trainer";
        mockMvc.perform(put(url)
                .content(trainerPesel))
                .andExpect(status().isCreated());
        //When
        var sportsmanEntity = repository.findById(sportsmanPesel).get();
        //Then
        assertEquals(trainerPesel, sportsmanEntity.getTrainer().getPesel());
    }

    @Test
    public void shouldCardioTrainingCountIncrease() throws Exception{
        //Given
        var sportsmanPesel = "97112848572";
        var url = URL + sportsmanPesel + "/cardio/train";
        //Then
        mockMvc.perform(put(url))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPowerTrainingCountIncrease() throws Exception{
        //Given
        var sportsmanPesel = "97112848572";
        var url = URL + sportsmanPesel + "/power/train";
        //Then
        mockMvc.perform(put(url))
                .andExpect(status().isOk());
    }
}