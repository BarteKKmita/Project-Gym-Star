package com.learning.Gym.Star.sportsmanbuilder;

import com.learning.Gym.Star.ListOfTrainers;
import com.learning.Gym.Star.Trainer;
import com.learning.Gym.Star.Training;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public interface SportsManBuilder {

    void chooseTrainer ();

    void chooseTrainingPlan();

    ConcreteSportsMan getSportsMan ();


    default String getTrainerData ( String name ) {
        System.out.println("Enter trainer " + name);
        Scanner enterTrainerName = new Scanner(System.in);
        return enterTrainerName.nextLine();
    }

    default Trainer trainerBuilder(){
    Trainer myTrainer=null;
    ListOfTrainers listOfTrainers = new ListOfTrainers();
    List <Trainer> trainers = listOfTrainers.getListOfTrainers();
        System.out.println("Available trainers list: ");
        for (Trainer trainer : trainers) {
        System.out.println(trainer.getName() + " " + trainer.getSurname());
    }
        System.out.println("Choose trainer you want to train with");
    String chosenName = getTrainerData("name");
        for (Trainer trainer : trainers) {
        //    && trainer.getSurname().equals(chosenSurname)
        if (trainer.getName().equals(chosenName)) {
            myTrainer = trainer;
        }
    }
        if (myTrainer == null) {
            System.out.println("There is no trainer with such name and surname.\n " +
                    " You were given trainer by default");
            myTrainer = trainers.get(0);
        }
    return myTrainer;
    }

    default Queue<Training> trainingPlanBuilder(Trainer myTrainer, ConcreteSportsMan sportsMan, int trainingDays){
        return myTrainer.preparePlan(sportsMan, trainingDays);
    }
}
