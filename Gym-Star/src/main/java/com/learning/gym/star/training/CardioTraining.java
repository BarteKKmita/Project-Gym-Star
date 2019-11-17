package com.learning.gym.star.training;

import com.learning.gym.star.statistics.TrainingStatistics;

public class CardioTraining implements TrainingType {

    @Override
    public int hashCode () {
        return super.hashCode();
    }

    @Override
    public boolean equals ( Object obj ) {
        return super.equals(obj);
    }

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
