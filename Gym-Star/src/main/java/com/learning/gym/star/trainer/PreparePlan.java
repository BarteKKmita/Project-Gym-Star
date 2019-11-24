package com.learning.gym.star.trainer;

import com.learning.gym.star.training.CardioTraining;
import com.learning.gym.star.training.PowerTraining;
import com.learning.gym.star.training.TrainingType;

import java.util.LinkedList;
import java.util.Queue;

public abstract class PreparePlan {

    public Queue <TrainingType> generateTrainingPlan (int trainingDays) {
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
    public abstract boolean isPowerTraining ( int trainingDay );

}
