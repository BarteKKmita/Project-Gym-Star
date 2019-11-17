package com.learning.gym.star.sportsman;

import com.learning.gym.star.sportsman.userinput.UserInput;
import com.learning.gym.star.statistics.DateStatisticsHandler;
import com.learning.gym.star.gym.GymDataHandler;
import com.learning.gym.star.trainer.ListOfTrainers;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.training.TrainingType;

import java.io.IOException;
import java.util.*;

public class SportsMan implements Gender {
    private final String name;
    private final String surname;
    private final GenderChoose gender;
    private Trainer chosenTrainer;
    private Queue <TrainingType> trainings;
    private final String path;
    private TrainingStatistics statistics = new TrainingStatistics();

    SportsMan ( String name, String surname, GenderChoose gender ) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        path = "data\\" + name + surname;
    }

    @Override
    public String getGender () {
        return gender.getGender();
    }

    public Trainer getChosenTrainer () {
        return chosenTrainer;
    }

    public void chooseTrainer () {
        UserInput userInput = new UserInput();
        List <Trainer> listOfTrainers = getTrainers();
        System.out.println("Available trainers list: ");
        for (Trainer trainer : listOfTrainers) {
            System.out.println(trainer.getName() + " " + trainer.getSurname());
        }
        String chosenName = userInput.getUserInput("name");
        String chosenSurname = userInput.getUserInput("surname");
        for (Trainer trainer : listOfTrainers) {
            //&& trainer.getSurname().equals(chosenSurname)
            if (trainer.getName().equals(chosenName) ) {
                chosenTrainer = trainer;
            }
        }
        if (chosenTrainer == null) {
            System.out.println("There is no trainer with such name and surname.\n " +
                    " You were given trainer by default");
            chosenTrainer = listOfTrainers.get(0);
        }
    }

    /**
     * Helper method to simulate SQL response
     */

    private List <Trainer> getTrainers () {
        ListOfTrainers trainers = new ListOfTrainers();
        return trainers.getListOfTrainers();
    }

    void chooseOtherTrainer () {
        chosenTrainer = null;
        chooseTrainer();
    }

    public void train () {
        if (trainings == null) {
            System.out.println("It's bad.");
        } else if (!trainings.isEmpty()) {
            TrainingType training = trainings.remove();
            DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
            dateStatisticsHandler.saveTrainingDateAndTimeStatistics(path);
            statistics.addStatistic(training);
            training.printTraining();
        } else {
            System.out.println("There is no trainings available. Ask your Trainer for training plan.");
        }
    }

    public void printAllStatistics () {
        for(TrainingType training: statistics.getAllTrainingsStatistics().keySet()){
            System.out.println(training.printStatistics(statistics));
        }
        printTrainingsDateAndTimeStats();
    }

    public void printStatistic ( TrainingType training ) {
        System.out.println(training.printStatistics(statistics));
    }

    void printTrainingsDateAndTimeStats () {
        try {
            System.out.println(new DateStatisticsHandler().readDateAndTimeStatistics(path));
        } catch (IOException e) {
            System.out.println("Cannot read " + path + " as a data file.");
            e.printStackTrace();
        }
    }

    public void showClosestGym ( int randomSeed ) {
        GymDataHandler gymDataHandler = new GymDataHandler();
        List <String> gymData = gymDataHandler.getGymData();
        Random random = new Random(randomSeed);
        System.out.println(gymData.get(random.nextInt(gymData.size())).trim());
    }

    public void getTrainingPlan ( int trainingDays ) {
        trainings = chosenTrainer.preparePlan(this, trainingDays);
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsMan sportsMan = (SportsMan) o;
        return name.equals(sportsMan.name) &&
                surname.equals(sportsMan.surname) &&
                gender == sportsMan.gender;
    }

    @Override
    public int hashCode () {
        return Objects.hash(name, surname, gender);
    }
}
