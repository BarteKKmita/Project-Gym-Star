package com.learning.gym.star.trainer;

public class FemalePlan extends PreparePlan {

    public boolean isPowerTraining ( int trainingDay ) {
        return trainingDay % 3 == 0;
    }
}
