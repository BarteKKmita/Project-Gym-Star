package com.learning.gym.star.training.cardio;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardioSystemTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetResponseFromRealDatabase() throws Exception{
        //Given
        int cardioId = 2;
        //When
        MockHttpServletResponse response = mockMvc.perform(get("/api/cardio/" + cardioId))
                .andReturn().getResponse();
        //Then
        assertNotNull(response);
    }
}
