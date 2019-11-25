package com.learning.gym.star.sportsman;

import com.learning.gym.star.sportsman.userinput.UserText;
import com.learning.gym.star.statistics.DateStatisticsHandler;
import com.learning.gym.star.gym.GymDataHandler;
import com.learning.gym.star.trainer.ListOfTrainers;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.training.TrainingType;
import lombok.*;

import java.io.IOException;
import java.util.*;

@Getter
@EqualsAndHashCode
public class SportsMan implements Gender {
    private final String name;
    private final String surname;
    private final GenderChoose gender;
    @EqualsAndHashCode.Exclude private Trainer chosenTrainer;
    @EqualsAndHashCode.Exclude private Queue <TrainingType> trainings;
    @EqualsAndHashCode.Exclude private final String path;
    @EqualsAndHashCode.Exclude private TrainingStatistics statistics = new TrainingStatistics();

    public SportsMan ( String name, String surname, GenderChoose gender ) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        path = "data\\" + name + surname;
    }

    @Override
    public String getGender () {
        return gender.getGender();
    }

    public void chooseTrainer ( UserText userInput ) {
        List <Trainer> listOfTrainers = getTrainers();
        String chosenName = userInput.getUserInput("name");
        String chosenSurname = userInput.getUserInput("surname");
        for (Trainer trainer : listOfTrainers) {
            if (trainer.getName().equals(chosenName) && trainer.getSurname().equals(chosenSurname)) {
                chosenTrainer = trainer;
            }
        }
        if (chosenTrainer == null) {
            System.out.println("There is no trainer with such name and surname.\n " +
                    " You were given trainer by default");
            chosenTrainer = listOfTrainers.get(0);
        }
    }

    private void printAvailableTrainers ( List <Trainer> listOfTrainers ) {
        System.out.println("Available trainers list: ");
        for (Trainer trainer : listOfTrainers) {
            System.out.println(trainer.getName() + " " + trainer.getSurname());
        }
    }

    /**
     * Helper method to simulate SQL response
     */

    private List <Trainer> getTrainers () {
        ListOfTrainers trainersList = new ListOfTrainers();
        List <Trainer> trainers = trainersList.getListOfTrainers();
        printAvailableTrainers(trainers);
        return trainers;
    }

    void chooseOtherTrainer ( UserText userInput ) {
        chosenTrainer = null;
        chooseTrainer(userInput);
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
        for (TrainingType training : statistics.getAllTrainingsStatistics().keySet()) {
            System.out.println(training.printStatistics(statistics));
        }
        printTrainingsDateAndTimeStats();
    }

    public void printStatistic ( TrainingType training ) {
        System.out.println(training.printStatistics(statistics));
    }

    private void printTrainingsDateAndTimeStats () {
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
}
