package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.sportsmanbuilder.userinput.UserInputForTests;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.training.CardioTraining;
import com.learning.gym.star.training.PowerTraining;
import com.learning.gym.star.training.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConcreteSportsManTest {
    private ConcreteSportsMan sportsMan;
    private Queue <TrainingType> mockedListOfTraining;
    @Mock
    Trainer trainer;

    //kiedy mock'uje za pomocą adnotacji typ interfejsu to dostaje nulla a potem nullPointException przy wywołaniu
    @BeforeEach
    void setUp () {
        mockedListOfTraining = mock(LinkedList.class);
        sportsMan = new ConcreteSportsMan("Bartek", "Surname", GenderChoose.M, trainer, mockedListOfTraining, "data\\Bartek", mock(TrainingStatistics.class));
    }

    // W tym teście całkowicie trace kontrolę nad działaniem najmniejszego fragmentu kodu.
    // Odwołuję się do metody obiektu który został utworzony wewnątrz metody chooseOtherTrainer.
    // Nie jestem go w stanie ani mock'ować ani spy'ować.
    // W tym konkretnym przypadku się nic nie dzieje ale gdyby to było np podbieranie danych z jakiejś strony a ona by nie odpowiadała test by nie przeszedł.
    // Widzę problem i muszę pomyśleć nad rozwiązaniem.
    //TODO
    @Test
    void shouldSelectTrainerWeronikaFromList () {
        //Given
        String expectedName = "Weronika";
        String expectedSurname = "Kosmowska";
        //When
        sportsMan.chooseOtherTrainer(new UserInputForTests(expectedName, expectedSurname));
        //Then
        assertEquals(expectedName, sportsMan.getMyTrainer().getName());
        assertEquals(expectedSurname, sportsMan.getMyTrainer().getSurname());
    }

    @Test
    void shouldPrintTrainingMessages () {
        //Given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        when(mockedListOfTraining.remove()).thenReturn(new PowerTraining());
        //When
        sportsMan.train();
        //Then
        verify(out).println(startsWith("Doing power training"));
    }

    @Test
    void shouldPrintNoTrainingsAvailableWhenEmptyList () {
        //Given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        when(mockedListOfTraining.isEmpty()).thenReturn(Boolean.TRUE);
        //When
        sportsMan.train();
        //Then
        verify(out).println(endsWith("plan."));
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

    @Test
    void shouldPrintItsBadWhenCallingNullTrainingPlan () {
        //Given
        sportsMan = new ConcreteSportsMan("Bartek", "Surname", GenderChoose.M, mock(Trainer.class), null, "data\\Bartek", mock(TrainingStatistics.class));
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        //When
        sportsMan.train();
        //Then
        verify(out).println(contains("It's bad."));
    }

    @Test
    void shouldChooseOtherTrainingPlan () {
        //Given
        int trainingDays = 3;
        int expectedTrainingDays = 2;
        when(trainer.preparePlan(any(), anyInt())).thenReturn(getTrainingTypes());
        //When
        sportsMan.chooseOtherTrainingPlan(trainingDays);
        //then
        assertEquals(expectedTrainingDays, sportsMan.getTrainings().size());
    }

    private LinkedList <TrainingType> getTrainingTypes () {
        LinkedList <TrainingType> trainingTypes = new LinkedList <>();
        trainingTypes.add(new PowerTraining());
        trainingTypes.add(new CardioTraining());
        return trainingTypes;
    }
}

