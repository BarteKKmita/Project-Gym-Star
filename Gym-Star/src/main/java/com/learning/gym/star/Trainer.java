package com.learning.gym.star;

import java.util.*;

public class Trainer {
    private final String name;
    private final String surname;
    private int costPerHour;
    private Set <SportsMan> sportsMenList = new HashSet <>();

    Trainer ( String name, String surname, int costPerHour ) {
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

    int getCostPerHour () {
        return costPerHour;
    }

    public Queue <TrainingType> preparePlan ( Gender gender, int trainingDays ) {
        Plan plan = new Plan();
        return plan.generateTrainingPlan(gender, trainingDays);
    }

    void addSportsMan ( SportsMan sportsMan ) {
        sportsMenList.add(sportsMan);
    }

    void removeSportsMan ( SportsMan sportsMan) {
        if (sportsMenList.contains(sportsMan)) {
            sportsMenList.remove(sportsMan);
        } else {
            System.out.println("There is no sports man at specified index.");
        }
    }

    Set <SportsMan> getSportsMenList () {
        return sportsMenList;
    }
}
