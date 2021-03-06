package com.learning.gym.star.statistics;

import com.learning.gym.star.training.TrainingType;

import java.util.HashMap;
import java.util.Map;

public class TrainingStatistics {

    private Map <TrainingType, Integer> trainingsStatistics;

    public Map <TrainingType, Integer> getAllTrainingsStatistics () {
        return trainingsStatistics;
    }

    public TrainingStatistics () {
        this.trainingsStatistics = new HashMap <>();
    }

    public void addStatistic ( TrainingType training ) {
        if (trainingsStatistics.containsKey(training)) {
            trainingsStatistics.put(training, trainingsStatistics.get(training) + 1);
        } else {
            trainingsStatistics.put(training, 1);
        }
    }

    public int getSpecificTrainingTypeStatistics(TrainingType training){
        if(trainingsStatistics.containsKey(training)){
            return trainingsStatistics.get(training);
        }
        return 0;
    }

    public int getAllTrainingsCount(){
        int countTrainings=0;
        for(TrainingType trainings:trainingsStatistics.keySet()){
            countTrainings+=trainingsStatistics.get(trainings);
        }
        return countTrainings;
    }
}
