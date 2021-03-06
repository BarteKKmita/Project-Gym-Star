package com.learning.gym.star.training.power;

import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.training.TrainingType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PowerTraining implements TrainingType {
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
