package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.userinput.UserInputForTests;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.training.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class SportsManTest {

    private SportsMan sportsMan;

    @BeforeEach
    void setUp(){
        sportsMan = new MaleSportsMan(mock(ConcreteSportsMan.class));
    }

    @Test
    void shouldReturnSpecifiedTrainer(){
        //Given
        String expectedName = "Mariusz";
        String expectedSurname = "Gawryś";
        //When
        Trainer trainer = sportsMan.trainerBuilder(new UserInputForTests(expectedName, expectedSurname));
        //Then
        assertEquals(expectedName, trainer.getName());
    }

    @Test
    void shouldReturnTrainingPlan(){
        //Given
        Trainer trainer = spy(Trainer.class);
        int trainingDays = 3;
        //When
        Queue<TrainingType> trainings = sportsMan.trainingPlanBuilder(trainer, mock(ConcreteSportsMan.class), 3);
        //Then
        assertEquals(trainingDays, trainings.size());
    }
}