package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsman.Gender;
import com.learning.gym.star.sportsman.SportsMan;
import com.learning.gym.star.training.TrainingType;

import java.util.*;

public class Trainer {
    private String name;
    private String surname;
    private int costPerHour;
    private Set <SportsMan> sportsMenList = new LinkedHashSet <>();

    public Trainer () {
    }

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

    void removeSportsMan ( SportsMan sportsMan ) {
        if (sportsMenList.contains(sportsMan)) {
            sportsMenList.remove(sportsMan);
        } else {
            System.out.println("There is no such sportsman.");
        }
    }

    Set <SportsMan> getSportsMenList () {
        return sportsMenList;
    }

    String printSportsMen () {
        StringBuilder sportsMenNames = new StringBuilder();
        List <SportsMan> sportsMen = new ArrayList <>(sportsMenList);
        for (int i = 0; i < sportsMen.size(); i++) {
            sportsMenNames.append(sportsMen.get(i).getName()).append(", ");
        }
        return sportsMenNames.toString();
    }

    String printSportsManAuxiliary () {
        return printSportsMenRecursive(new ArrayList <>(sportsMenList), 0, "");
    }

    String printSportsMenRecursive ( List <SportsMan> list, int i, String sportsMenNames ) {
        if (list.size() == i) {
            return sportsMenNames;
        }
        sportsMenNames += list.get(i).getName() + ", ";
        i++;
        return printSportsMenRecursive(list, i, sportsMenNames);
    }

}
