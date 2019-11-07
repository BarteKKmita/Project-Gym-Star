package com.learning.Gym.Star;

public class CardioTrening extends Training {

    @Override
    public boolean isPowerTraining () {
        return false;
    }

    @Override
    public boolean isCardioTraining () {
        return true;
    }
}
