package com.learning.gym.star.training;

import com.learning.gym.star.statistics.TrainingStatistics;

public interface TrainingType {
     void printTraining();

     String printStatistics( TrainingStatistics statistics);
}
