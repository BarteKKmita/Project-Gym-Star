package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.gym.database.jpa.GymJpaRepository;
import com.learning.gym.star.gym.service.jpa.GymServiceJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GymControllerJpaIntegrationTest {

    @Autowired
    GymControllerJpa controllerJpa;
    GymJpaRepository repository;
    GymServiceJpa serviceJpa;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldControllerNotBeNullWhenAutowired(){
        assertNotNull(controllerJpa);
    }

    @Test
    public void shouldGetFirstGymWhenGetGymWithId1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jpa/gym/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gymName").value("pierwsza"));
    }
}
