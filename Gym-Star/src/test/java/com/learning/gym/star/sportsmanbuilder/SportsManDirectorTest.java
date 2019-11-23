package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.userinput.ConsoleUserInput;
import com.learning.gym.star.sportsman.userinput.UserInputForTests;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.training.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SportsManDirectorTest {

    @Test
    void shouldReturnConcreteSportsMan () {
        //Given
        String expectedName= "Bartek";
        String expectedSurname= "Kmita";
        SportsManDirector sportsManDirector = new SportsManDirector(mock(MaleSportsMan.class));
        when(sportsManDirector.getConcreteSportsMan()).thenReturn(new ConcreteSportsMan(expectedName, expectedSurname));
        //When
        ConcreteSportsMan concreteSportsMan = sportsManDirector.getConcreteSportsMan();
        //Then
        assertEquals(expectedName, concreteSportsMan.getName());
        assertEquals(expectedSurname, concreteSportsMan.getSurname());
    }
}