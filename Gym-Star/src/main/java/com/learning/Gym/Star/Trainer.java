package com.learning.Gym.Star;

import java.util.*;

class Trainer {
    private final String name;
    private final String surname;
    private int costPerHour;
    private List <SportsMan> sportsMenList = new ArrayList <>();

    Trainer ( String name, String surname, int costPerHour ) {
        this.name = name;
        this.surname = surname;
        this.costPerHour = costPerHour;
    }

    public void setCostPerHour ( int costPerHour ) {
        this.costPerHour = costPerHour;
    }

    String getName () {
        return name;
    }

    String getSurname () {
        return surname;
    }

    int getCostPerHour () {
        return costPerHour;
    }

    Queue <Training> preparePlan ( Gender gender, int trainingDays ) {
        Plan plan = new Plan();
        return plan.generateTrainingPlan(gender, trainingDays);
    }

    void addSportsMan ( SportsMan sportsMan ) {
        sportsMenList.add(sportsMan);
    }

    void removeSprotsMan ( int index ) {
        if (index < sportsMenList.size()) {
            sportsMenList.remove(index);
        } else {
            System.out.println("There is no sports man at specified index.");
        }
    }

    List <SportsMan> getSportsMenList () {
        return sportsMenList;
    }
}
