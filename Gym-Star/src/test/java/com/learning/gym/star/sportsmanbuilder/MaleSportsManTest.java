package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsmanbuilder.userinput.UserInputForTests;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.trainer.Trainer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MaleSportsManTest {

    private ConcreteSportsMan sportsMan;

    @Test
    void shouldReturnChosenTrainer () {
        //Given
        sportsMan = new ConcreteSportsMan("Name", "Surname", GenderChoose.M, mock(Trainer.class), mock(LinkedList.class), "data\\Test", mock(TrainingStatistics.class));
        String expectedName = "Mariusz";
        String expectedSurname = "Gawryś";
        MaleSportsMan maleSportsMan = new MaleSportsMan(sportsMan);
        //When
        maleSportsMan.chooseTrainer(new UserInputForTests(expectedName, expectedSurname));
        ConcreteSportsMan testingSportsMan = maleSportsMan.getSportsMan();
        //Then
        assertEquals(expectedName, testingSportsMan.getMyTrainer().getName());
        assertEquals(expectedSurname, testingSportsMan.getMyTrainer().getSurname());
    }

    @Test
    void shouldReturnTrainingPlan () {
        //Given
        //Zostawienie mock trainer.class daje null point exception
        sportsMan = new ConcreteSportsMan("Name", "Surname", GenderChoose.M, spy(Trainer.class), mock(LinkedList.class), "data\\Test", mock(TrainingStatistics.class));
        int trainingDays= 3;
        int expectedListSize = 3;
        MaleSportsMan maleSportsMan = new MaleSportsMan(sportsMan);
        //When
        maleSportsMan.chooseTrainingPlan(trainingDays);
        ConcreteSportsMan testingSportsMan = maleSportsMan.getSportsMan();
        //Then
        assertThat(testingSportsMan.getTrainings(), Matchers.hasSize(expectedListSize));
    }
}