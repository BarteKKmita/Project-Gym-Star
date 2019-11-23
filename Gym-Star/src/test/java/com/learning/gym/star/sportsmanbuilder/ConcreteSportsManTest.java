package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.training.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ConcreteSportsManTest {
    private ConcreteSportsMan sportsMan;

    @BeforeEach
    void setUp () {
        Queue<TrainingType> mock = mock(LinkedList.class);
        sportsMan = new ConcreteSportsMan("Bartek", "Surname", GenderChoose.M,spy(Trainer.class), mock,"data\\Bartek",mock(TrainingStatistics.class));
    }

//    @Test
//    void shouldSelectTrainerMariuszFromList () {
//        //Given
//        ByteArrayInputStream testIn;
//        String data = "Weronika";
//        testIn = new ByteArrayInputStream(data.getBytes());
//        System.setIn(testIn);
//        //When
//        sportsMan.chooseOtherTrainer();
//        //Then
//        assertEquals(data, sportsMan.getMyTrainer().getName());
//    }
//
//    @Test
//    void shouldPrintTrainingMessages () {
//        //Given
//        PrintStream out = mock(PrintStream.class);
//        System.setOut(out);
//        //Then
//        sportsMan.train();
//        sportsMan.train();
//        verify(out).println(startsWith("Doing power training"));
//        verify(out).println(endsWith("Doing cardio training"));
//    }
//
//    @Test
//    void shouldPrintNoTrainingsAvailableWhenEmptyList () {
//        //Given
//        PrintStream out = mock(PrintStream.class);
//        System.setOut(out);
//        //Then
//        sportsMan.train();
//        sportsMan.train();
//        sportsMan.train();
//        sportsMan.train();
//        verify(out).println(endsWith("plan."));
//    }
//
//    @Test
//    void shouldReturn82WhenSeed40showClosestGym () {
//        //Given
//        int seed = 40;
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        //When
//        sportsMan.showClosestGym(seed);
//        //Then
//        assertEquals("82", outContent.toString().trim());
//    }
//
//    @Test
//    void shouldPrintItsBadWhenCallingNullTrainingPlan () {
//        //When
//        PrintStream out = mock(PrintStream.class);
//        System.setOut(out);
//        //Then
//        sportsMan.withTrainings(null);
//        sportsMan.train();
//        verify(out).println(contains("It's bad."));
    }

