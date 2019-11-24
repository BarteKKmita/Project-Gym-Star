package com.learning.gym.star.trainer;

public class MalePlan extends PreparePlan {

    @Override
    public boolean isPowerTraining ( int trainingDay ) {
        return trainingDay % 2 == 0;
    }
}
