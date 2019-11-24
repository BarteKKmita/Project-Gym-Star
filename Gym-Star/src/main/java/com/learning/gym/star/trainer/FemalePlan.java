package com.learning.gym.star.trainer;

import com.learning.gym.star.training.CardioTraining;
import com.learning.gym.star.training.PowerTraining;
import com.learning.gym.star.training.TrainingType;

import java.util.LinkedList;
import java.util.Queue;

public class FemalePlan extends PreparePlan {

    public boolean isPowerTraining ( int trainingDay ) {
        return trainingDay % 3 == 0;
    }
}
