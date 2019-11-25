package com.learning.gym.star;

import com.learning.gym.star.sportsman.ConcreteSportsMan;
import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.sportsman.SportsMan;
import com.learning.gym.star.sportsman.userinput.UserInputForTests;
import com.learning.gym.star.training.CardioTraining;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

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
    void shouldSelectTrainerMariuszFromList () {
        //Given
        String expectedName = "Mariusz";
        String expectedSurname = "Gawryś";
        //When
        sportsMan.chooseTrainer(new UserInputForTests(expectedName, expectedSurname));
        //Then
        assertEquals(expectedName, sportsMan.getChosenTrainer().getName());
        assertEquals(expectedSurname, sportsMan.getChosenTrainer().getSurname());
    }

    @Test
    void shouldPrintTrainingMessages () {
        //Given
        int trainingDays=3;
        String trainerName = "Mariusz";
        String trainerSurname = "Gawryś";
        sportsMan.chooseTrainer(new UserInputForTests(trainerName, trainerSurname));
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
        int trainingDays = 2;
        String trainerName = "Mariusz";
        String trainerSurname = "Gawryś";
        sportsMan.chooseTrainer(new UserInputForTests(trainerName, trainerSurname));
        sportsMan.getTrainingPlan(trainingDays);
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
        String trainerName = "Mariusz";
        String trainerSurname = "Gawryś";
        sportsMan.chooseTrainer(new UserInputForTests(trainerName, trainerSurname));
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
        String trainerName = "Mariusz";
        String trainerSurname = "Gawryś";
        sportsMan.chooseTrainer(new UserInputForTests(trainerName, trainerSurname));
        sportsMan.getTrainingPlan(trainingDays);
        //When
        sportsMan.train();
        sportsMan.train();
        sportsMan.train();
        //Then
        sportsMan.printStatistic(new CardioTraining());
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

    @Test
    void shouldClassBeReflexive(){
        //Given
        SportsMan sportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        //Then
        assertEquals(sportsMan, sportsMan);
    }

    @Test
    void shouldClassBeSymmetric (){
        //Given
        SportsMan sportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        SportsMan secondSportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        //Then
        assertTrue(sportsMan.equals( secondSportsMan)&&secondSportsMan.equals(sportsMan));
    }

    @Test
    void shouldBeFalseWhenComparingDifferentObjects (){
        //Given
        SportsMan sportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        SportsMan secondSportsMan = new SportsMan("AnotherName", "Surname", GenderChoose.M);
        //Then
        assertFalse(sportsMan.equals( secondSportsMan)&&secondSportsMan.equals(sportsMan));
    }

    @Test
    void shouldClassBeTransitive(){
        //Given
        SportsMan sportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        SportsMan secondSportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        SportsMan thirdSportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        //When
        if(sportsMan.equals(secondSportsMan)&& secondSportsMan.equals(thirdSportsMan)){
            //Then
            assertEquals(secondSportsMan, thirdSportsMan);
        }
    }

    @Test
    void shouldExcludedFieldsNoChangeEquals(){
        //Given
        String trainerName= "Mariusz";
        String trainerSurname = "Gawryś";
        SportsMan sportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        SportsMan secondSportsMan = new SportsMan("Name", "Surname", GenderChoose.M);
        //When
        sportsMan.chooseTrainer(new UserInputForTests(trainerName, trainerSurname));
        //Then
        assertEquals(secondSportsMan, sportsMan);
    }
}
