package com.learning.gym.star.trainer.trainerdb.controller;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TrainerControllerWithEmbeddedMySQLTest {
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
    public void shouldGetAllTrainers() throws Exception{
        //Given
        var url = "/api/trainer/all";
        var firstTrainerPesel = "57122299175";
        var secondTrainerPesel = "82062972549";
        //When
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$.[0].pesel").value(firstTrainerPesel))
                .andExpect(jsonPath("$.[1].pesel").value(secondTrainerPesel));
    }
}
