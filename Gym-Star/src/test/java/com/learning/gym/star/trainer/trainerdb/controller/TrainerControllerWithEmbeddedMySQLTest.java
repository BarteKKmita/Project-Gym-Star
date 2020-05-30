package com.learning.gym.star.trainer.trainerdb.controller;

import com.learning.gym.star.ControllerWithMySQLTestContextSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TrainerControllerWithEmbeddedMySQLTest extends ControllerWithMySQLTestContextSpecification {

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
