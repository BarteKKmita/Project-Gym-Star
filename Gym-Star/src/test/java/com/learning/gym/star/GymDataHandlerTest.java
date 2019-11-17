package com.learning.gym.star;

import com.learning.gym.star.gym.GymDataHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymDataHandlerTest {

    @Test
    void shouldReturn90RecordsWithGymData () {
        //Given
        GymDataHandler gymDataHandler = new GymDataHandler();
        int listSizeExpected = 90;
        //When
        int output = gymDataHandler.getGymData().size();
        //Then
        assertEquals(listSizeExpected, output);
    }
}
