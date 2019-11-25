package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.gym.GymDataHandler;
import com.learning.gym.star.sportsman.Gender;
import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.sportsman.userinput.UserText;
import com.learning.gym.star.statistics.DateStatisticsHandler;
import com.learning.gym.star.trainer.Trainer;
import com.learning.gym.star.statistics.TrainingStatistics;
import com.learning.gym.star.training.TrainingType;
import lombok.*;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Random;

@Getter
@AllArgsConstructor
@With
@EqualsAndHashCode
public class ConcreteSportsMan implements Gender {
    private final String name;
    private final String surname;
    private GenderChoose gender;
    @EqualsAndHashCode.Exclude private Trainer myTrainer;
    @EqualsAndHashCode.Exclude private Queue <TrainingType> trainings;
    @EqualsAndHashCode.Exclude private final String path;
    @EqualsAndHashCode.Exclude private TrainingStatistics statistics = new TrainingStatistics();

    public ConcreteSportsMan ( String name, String surname ) {
        this.name = name;
        this.surname = surname;
        path = "data\\" + name + surname;
    }

    @Override
    public String getGender () {
        return gender.getGender();
    }

    void showClosestGym ( int randomSeed ) {
        GymDataHandler gymDataHandler = new GymDataHandler();
        List <String> gymData = gymDataHandler.getGymData();
        Random random = new Random(randomSeed);
        System.out.println(gymData.get(random.nextInt(gymData.size())).trim());
    }

    void printAllStatistics () {
        for (TrainingType training : statistics.getAllTrainingsStatistics().keySet()) {
            System.out.println(training.printStatistics(statistics));
        }
        printTrainingsDateAndTimeStats();
    }

    void printStatistic ( TrainingType training ) {
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

    void train () {
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

    //Ta metoda nie zależy od płci. Żeby pisać obiektowo przydałoby się to jednak zmienić.
    // Np podobne wywyołanie metody dla chooseOtherTrainingPlan zakończyłoby się klęską :)
    void chooseOtherTrainer ( UserText userInput ) {
        SportsMan sportsMan = new MaleSportsMan(this);
        this.myTrainer=sportsMan.trainerBuilder(userInput);;
    }

    void chooseOtherTrainingPlan ( int trainingDays ) {
        trainings = myTrainer.preparePlan(this, trainingDays);
    }
}
