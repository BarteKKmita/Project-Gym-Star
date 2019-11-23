package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.sportsman.userinput.UserText;
import com.learning.gym.star.trainer.ListOfTrainers;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.training.TrainingType;

import java.util.List;
import java.util.Queue;

public abstract class SportsMan implements SportsManBuilder {

    Trainer trainerBuilder( UserText userInput ){
        ListOfTrainers listOfTrainers = new ListOfTrainers();
        List <Trainer> trainers = listOfTrainers.getListOfTrainers();
        String chosenName = userInput.getUserInput("name");
        String chosenSurname = userInput.getUserInput("surname");
        return selectTrainer(trainers, chosenName, chosenSurname);
    }

    Queue <TrainingType> trainingPlanBuilder( Trainer myTrainer, ConcreteSportsMan sportsMan, int trainingDays){
        return myTrainer.preparePlan(sportsMan, trainingDays);
    }

    private Trainer selectTrainer ( List <Trainer> trainers, String chosenName, String chosenSurname ) {
        Trainer chosenTrainer=null;
        for (Trainer trainer : trainers) {
            if (trainer.getName().equals(chosenName) && trainer.getSurname().equals(chosenSurname)) {
                chosenTrainer = trainer;
            }
        }
        if (chosenTrainer == null) {
            System.out.println("There is no trainer with such name and surname.\n " +
                    " You were given trainer by default");
            chosenTrainer = trainers.get(0);
        }
        return chosenTrainer;
    }
}
