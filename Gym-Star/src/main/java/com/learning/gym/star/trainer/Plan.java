package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsman.Gender;
import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.training.TrainingType;


import java.util.Queue;

public class Plan {
    public Queue <TrainingType> generateTrainingPlan ( Gender gender, int trainingDays ) {
        if (GenderChoose.M.getGender().equals(gender.getGender())) {
            return new MalePlan().generateTrainingPlan(trainingDays);
        }
        return new FemalePlan().generateTrainingPlan(trainingDays);
    }
}
