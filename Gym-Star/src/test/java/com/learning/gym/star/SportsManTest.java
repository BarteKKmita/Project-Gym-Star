package com.learning.gym.star;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Adnotacje. Wiem, że są ale jeszcze muszę je opanować :)
 */

public class SportsManTest {
    private ConcreteSportsMan sportsMan;

    @BeforeEach
    void initListOfTrainers () {
        sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
    }

    @Test
    void shouldReturnMaleWhenM () {
        //Given
        String expectedGender = "Male";
        //When
        String outPutGender = sportsMan.getGender();
        //Then
        assertEquals(expectedGender, outPutGender);
    }

    @Test
    void shouldReturnFailWhenFemale () {
        //Given
        String expectedGender = "Female";
        //When
        String outPutGender = sportsMan.getGender();
        //Then
        assertNotEquals(expectedGender, outPutGender);
    }

    @Test
    void shouldReturnInputString () {
        //Given
        ByteArrayInputStream testIn;
        String expectedName = "Mariusz";
        testIn = new ByteArrayInputStream(expectedName.getBytes());
        System.setIn(testIn);
        //When
        sportsMan.chooseTrainer();
        //Then
        assertEquals(expectedName, sportsMan.getMyTrainer().getName());
    }

    // Trzy niedziałające testy
    @Test
    void shouldSelectTrainerMariuszFromList () {
        //Given
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        //ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        //When
        sportsMan.chooseTrainer();
        //Then
        assertEquals(data, sportsMan.getMyTrainer().getName());
    }

    @Test
    void shouldSelectTrainerMariuszFromList2 () {
        //Given

        ByteArrayInputStream testInName;
        String name = "Mariusz";
        testInName = new ByteArrayInputStream(name.getBytes());
        System.setIn(testInName);
        ByteArrayInputStream testInSurname;
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        //When
        String surname = "Gawryś";
        testInSurname = new ByteArrayInputStream(surname.getBytes());
        System.setIn(testInSurname);
        sportsMan.chooseTrainer();
        //Then

        assertEquals(name, sportsMan.getMyTrainer().getName());
        //assertEquals(surname, sportsMan.getMyTrainer().getSurname());
    }

    @Test
    void shouldSelectTrainerMariuszFromList3 () {
        //Given
        final TextFromStandardInputStream systemInMock
                = emptyStandardInputStream();
        String name = "Mariusz";
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        systemInMock.provideLines("Mariusz", "Gawryś");
        sportsMan.chooseTrainer();
        //assertEquals(name,sportsMan.getMyTrainer().getName());
    }

    @Test
    void shouldPrintTrainingMessages () {
        //Given
        int trainingDays=3;
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        sportsMan.chooseTrainer();
        sportsMan.getTrainingPlan(trainingDays);
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //When
        sportsMan.train();
        //Then
        verify(out).println(matches("Doing power training"));
    }

    @Test
    void shouldPrintNoTrainingsAvailableWhenEmptyList () {
        //Given
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        sportsMan.chooseTrainer();
        sportsMan.getTrainingPlan(2);
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //When
        sportsMan.train();
        sportsMan.train();
        sportsMan.train();
        //Then
        verify(out).println(endsWith("plan."));
    }

    @Test
    void shouldPrintItsBadWhenCallingNullTrainingPlan () {
        //Given
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        sportsMan.chooseTrainer();
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //When
        sportsMan.train();
        //Then
        verify(out).println(contains("It's bad."));
    }

    @Test
    void shouldPrintDateStatistics () {
        //Given
        int trainingDays = 3;
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        sportsMan.chooseTrainer();
        sportsMan.getTrainingPlan(trainingDays);
        //When
        sportsMan.train();
        sportsMan.train();
        sportsMan.train();
        //Then
        sportsMan.printStatistic(new CardioTraning());
        sportsMan.printAllStatistics();
    }

    @Test
    void shouldReturnExpectedGymWhenAskingForClosestGym () {
        //Given
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ConcreteSportsMan sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
        int seed = 40;
        //When
        sportsMan.showClosestGym(seed);
        //Then
        assertEquals("82", outContent.toString().trim());
    }
}
