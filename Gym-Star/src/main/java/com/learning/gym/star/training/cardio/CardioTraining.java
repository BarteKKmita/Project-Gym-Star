package com.learning.gym.star.training.cardio;

import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.training.TrainingType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CardioTraining implements TrainingType {

    @Override
    public void printTraining () {
        System.out.println("Doing cardio training");
    }

    @Override
    public String printStatistics ( TrainingStatistics statistics ) {
        int cardioTrainingCount = statistics.getSpecificTrainingTypeStatistics(this);
        return "Cardio training count: " +cardioTrainingCount;
    }
}
