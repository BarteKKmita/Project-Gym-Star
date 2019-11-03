package com.learning.Gym.Star;

import java.util.*;

public class Trainer {
    private final String name;
    private final String surname;
    private int costPerHour;
    public Trainer ( String name, String surname, int costPerHour ) {
        this.name = name;
        this.surname = surname;
        this.costPerHour = costPerHour;
    }

    public void setCostPerHour ( int costPerHour ) {
        this.costPerHour = costPerHour;
    }



    public String getName () {
        return name;
    }

    public String getSurname () {
        return surname;
    }

    public int getCostPerHour () {
        return costPerHour;
    }

    Queue <TrainingType> preparePlan( Gender gender){
        Queue<TrainingType> trainings = new LinkedList <>();
        return trainings;
    }
}
