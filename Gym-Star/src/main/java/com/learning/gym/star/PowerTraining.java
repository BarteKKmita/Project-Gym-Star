package com.learning.gym.star;

import java.util.Objects;

public class PowerTraining implements TrainingType {
    private final boolean isPowerTraining = true;

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerTraining that = (PowerTraining) o;
        return isPowerTraining == that.isPowerTraining;
    }

    @Override
    public int hashCode () {
        return Objects.hash(isPowerTraining);
    }

    @Override
    public void printTraining () {
        System.out.println("Doing power training");
    }
}
