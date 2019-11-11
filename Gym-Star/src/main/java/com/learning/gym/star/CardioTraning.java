package com.learning.gym.star;

import java.util.Objects;

public class CardioTraning implements TrainingType {
   private final boolean isCardioTraining = true;

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardioTraning that = (CardioTraning) o;
        return isCardioTraining == that.isCardioTraining;
    }

    @Override
    public int hashCode () {
        return Objects.hash(isCardioTraining);
    }

    @Override
    public void printTraining () {
        System.out.println("Doing cardio training");
    }
}
