package com.learning.gym.star.training.power;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PowerSystemTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn0TrainingCountAfterResettingTrainingCountStats() throws Exception{
        //Given
        int powerId = 2;
        String expectedTrainingCount = "0";
        //When
        mockMvc.perform(put("/api/power/reset/" + powerId));
        MockHttpServletResponse response = mockMvc.perform(get("/api/power/" + powerId)).andReturn().getResponse();
        int outputStatus = response.getStatus();
        //Then
        assertEquals(HttpStatus.OK.value(), outputStatus);
        assertEquals(expectedTrainingCount, response.getContentAsString());
    }
}
