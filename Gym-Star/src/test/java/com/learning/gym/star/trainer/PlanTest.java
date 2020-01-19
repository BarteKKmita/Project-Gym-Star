package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsmanbuilder.gender.Gender;
import com.learning.gym.star.training.TrainingType;
import com.learning.gym.star.training.cardio.CardioTraining;
import com.learning.gym.star.training.power.PowerTraining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlanTest {

    private int trainingDays;
    private Plan plan;

    @BeforeEach
    void setUp(){
         plan = new Plan();
         trainingDays = 4;
    }

    @Test
    void shouldReturnCardioTrainingIn4thTrainingDayWhenMale () {
        String selectedGender = "Male";
        //When
        Queue <TrainingType> trainings = getTrainingPlan(selectedGender);
        trainings.remove();
        trainings.remove();
        trainings.remove();
        //Then
        assertEquals(CardioTraining.class, trainings.remove().getClass());;
    }

    @Test
    void shouldReturnPowerTrainingIn4thTrainingDayWhenFemale () {
        String selectedGender = "Female";
        //When
        Queue <TrainingType> trainings = getTrainingPlan(selectedGender);
        trainings.remove();
        trainings.remove();
        trainings.remove();
        //Then
        assertEquals(PowerTraining.class, trainings.remove().getClass());;
    }

    private Queue <TrainingType> getTrainingPlan ( String selectedGender ) {
        return plan.generateTrainingPlan(
                new Gender() {
                    @Override
                    public String getGender () {
                        return selectedGender;
                    }},
                trainingDays);
    }
}