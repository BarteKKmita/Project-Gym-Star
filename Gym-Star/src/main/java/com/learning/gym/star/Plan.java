package com.learning.gym.star;

import java.util.LinkedList;
import java.util.Queue;

class Plan {

    Queue <TrainingType> generateTrainingPlan ( Gender gender, int trainigDays ) {
        Queue <TrainingType> trainings = new LinkedList <>();
        for (int i = 0; i < trainigDays; i++) {
            if (isPowerTraining(i)) {
                trainings.add(new PowerTraining());
            } else {
                trainings.add(new CardioTraning());
            }
        }
        return trainings;
    }

    private boolean isPowerTraining(int trainingDay){
        return trainingDay%2==0;
    }
}
