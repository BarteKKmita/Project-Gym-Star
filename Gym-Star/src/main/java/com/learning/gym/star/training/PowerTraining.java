package com.learning.gym.star.training;

import com.learning.gym.star.statistics.TrainingStatistics;

import java.util.Objects;

public class PowerTraining implements TrainingType {

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
        System.out.println("Doing power training");
    }

    @Override
    public String printStatistics ( TrainingStatistics statistics ) {
        int powerTrainingCount = statistics.getSpecificTrainingTypeStatistics(this);
        return "Power training count: " +powerTrainingCount;
    }

}
