package com.learning.Gym.Star;

public class PowerTraining extends TrainingType {
    @Override
    public boolean isPowerTraining () {
        return true;
    }

    @Override
    public boolean isCardioTraining () {
        return false;
    }
}
