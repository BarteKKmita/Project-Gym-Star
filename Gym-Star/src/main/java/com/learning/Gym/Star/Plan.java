package com.learning.Gym.Star;

import java.util.LinkedList;
import java.util.Queue;

class Plan {

    Queue <Training> generateTrainingPlan ( Gender gender, int trainigDays ) {
        Queue <Training> trainings = new LinkedList <>();
        for (int i = 0; i < trainigDays; i++) {
            if (i % 2 == 0) {
                trainings.add(new PowerTraining());
            } else {
                trainings.add(new CardioTrening());
            }
        }
        return trainings;
    }
}
