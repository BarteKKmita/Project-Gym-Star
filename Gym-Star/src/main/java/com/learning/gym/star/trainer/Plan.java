package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsman.Gender;
import com.learning.gym.star.training.CardioTraining;
import com.learning.gym.star.training.PowerTraining;
import com.learning.gym.star.training.TrainingType;

import java.util.LinkedList;
import java.util.Queue;

public class Plan {
    //TODO
    public Queue <TrainingType> generateTrainingPlan ( Gender gender, int trainingDays ) {
        Queue <TrainingType> trainings = new LinkedList <>();
        for (int trainingDay = 0; trainingDay < trainingDays; trainingDay++) {
            if (isPowerTraining(trainingDay)) {
                trainings.add(new PowerTraining());
            } else {
                trainings.add(new CardioTraining());
            }
        }
        return trainings;
    }

    private boolean isPowerTraining(int trainingDay){
        return trainingDay%2==0;
    }
}
