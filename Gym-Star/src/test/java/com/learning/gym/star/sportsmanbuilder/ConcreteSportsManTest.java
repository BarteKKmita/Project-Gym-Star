package com.learning.gym.star.sportsmanbuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConcreteSportsManTest {
    private ConcreteSportsMan sportsMan;

    @BeforeEach
    void prepareSportsMan () {
        SportsManBuilder sportsManBuilder = new MaleSportsMan("Bartek", "Kmita");
        SportsManDirector director = new SportsManDirector(sportsManBuilder);
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        director.setConcreteSportsMan();
        sportsMan = director.getConcreteSportsMan();
    }

    @Test
    void shouldSelectTrainerMariuszFromList () {
        ByteArrayInputStream testIn;
        String data = "Weronika";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        //When
        sportsMan.chooseOtherTrainer();
        //Then
        assertEquals(data, sportsMan.getMyTrainer().getName());
    }

    @Test
    void shouldPrintTrainingMessages () {
        //Given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //Then
        sportsMan.train();
        sportsMan.train();
        sportsMan.train();
        verify(out).println(startsWith("Doing power training"));
    }

    @Test
    void shouldPrintNoTrainingsAvailableWhenEmptyList () {
        //Given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //Then
        sportsMan.train();
        sportsMan.train();
        sportsMan.train();
        verify(out).println(endsWith("plan."));
    }

    @Test
    void shouldPrintItsBadWhenCallingNullTrainingPlan () {
        //When
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //Then
        sportsMan.setTrainingPlan(null);
        sportsMan.train();
        verify(out).println(contains("It's bad."));
    }

    @Test
    void shouldReturn82WhenSeed40showClosestGym () {
        //Given
        int seed = 40;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //When
        sportsMan.showClosestGym(seed);
        //Then
        assertEquals("82", outContent.toString().trim());
    }
}
