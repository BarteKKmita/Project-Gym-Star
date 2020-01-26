package com.learning.gym.star.gym;

import com.learning.gym.star.gym.database.jdbc.GymRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymDataHandlerTest {

    @Test
    void shouldReturn90RecordsWithGymData(){
        //Given
        GymRepository gymDataHandler = new GymDataHandler();
        int listSizeExpected = 90;
        //When
        int output = gymDataHandler.getGymData().size();
        //Then
        assertEquals(listSizeExpected, output);
    }
}
