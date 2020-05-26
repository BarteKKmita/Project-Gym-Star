package com.learning.gym.star.training.cardio.controller;


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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerIntegrationWithEmbeddedMySQLTest {
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
    public void shouldNotBeNullWhenAutowired(){
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldIncrementTrainingCount() throws Exception{
        //Given
        int cardioId = 1;
        String expectedRequestBody = "3";
        //When
        mockMvc.perform(put("/api/cardio/train/" + cardioId));
        //Then
        mockMvc.perform(get("/api/cardio/" + cardioId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedRequestBody));
    }
}
