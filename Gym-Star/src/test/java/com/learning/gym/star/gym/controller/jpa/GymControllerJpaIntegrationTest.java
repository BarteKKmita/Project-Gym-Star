package com.learning.gym.star.gym.controller.jpa;

import com.learning.gym.star.EmbeddedMySqlProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GymControllerJpaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    private static void setUp(){
        EmbeddedMySqlProvider.setUpClass();
    }

    @Test
    public void shouldGetFirstGymWhenGetGymWithId1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jpa/gym/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gymName").value("pierwsza"));
    }
}
