package com.learning.gym.star.trainer;

import com.learning.gym.star.sportsmanbuilder.ConcreteSportsMan;
import com.learning.gym.star.sportsmanbuilder.gender.Gender;
import com.learning.gym.star.training.TrainingType;

import java.util.*;

public class Trainer {
    private String name;
    private String surname;
    private int costPerHour;
    private Set <ConcreteSportsMan> sportsMenList = new LinkedHashSet <>();

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

    void addSportsMan ( ConcreteSportsMan sportsMan ) {
        sportsMenList.add(sportsMan);
    }

    void removeSportsMan ( ConcreteSportsMan sportsMan ) {
        if (sportsMenList.contains(sportsMan)) {
            sportsMenList.remove(sportsMan);
        } else {
            System.out.println("There is no such sportsman.");
        }
    }

    Set <ConcreteSportsMan> getSportsMenList () {
        return sportsMenList;
    }

    String printSportsMen () {
        StringBuilder sportsMenNames = new StringBuilder();
        for (ConcreteSportsMan sportsMan : sportsMenList) {
            sportsMenNames.append(sportsMan.getName()).append(", ");
        }
        return sportsMenNames.toString();
    }

    String printSportsManAuxiliary () {
        return printSportsMenRecursive(new ArrayList <>(sportsMenList), 0, "");
    }

    private String printSportsMenRecursive ( List <ConcreteSportsMan> list, int i, String sportsMenNames ) {
        if (list.size() == i) {
            return sportsMenNames;
        }
        sportsMenNames += list.get(i).getName() + ", ";
        i++;
        return printSportsMenRecursive(list, i, sportsMenNames);
    }
}
