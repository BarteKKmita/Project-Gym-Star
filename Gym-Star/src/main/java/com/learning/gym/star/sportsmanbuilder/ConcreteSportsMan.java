package com.learning.gym.star.sportsmanbuilder;

import com.learning.gym.star.*;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ConcreteSportsMan implements Gender {
    private final String name;
    private final String surname;
    private GenderChoose gender;
    private Trainer myTrainer;
    private Queue <TrainingType> trainings;
    private final String PATH;
    private int cardioStatistics = 0;
    private int powerStatistics = 0;

    public ConcreteSportsMan ( String name, String surname ) {
        this.name = name;
        this.surname = surname;
        PATH = "data\\" + name + surname;
    }

    public void setGender ( GenderChoose gender ) {
        this.gender = gender;
    }

    public String getName () {
        return name;
    }

    public String getSurname () {
        return surname;
    }

    @Override
    public String getGender(){
        return gender.getGender();
    }

    public void setTrainer(Trainer trainer){
        this.myTrainer=trainer;
    }

    public void setTrainingPlan(Queue<TrainingType> trainings){
        this.trainings=trainings;
    }

    public Trainer getMyTrainer () {
        return myTrainer;
    }

    void showClosestGym ( int randomSeed ) {
        GymDataHandler gymDataHandler = new GymDataHandler();
        List <String> gymData = gymDataHandler.getGymData();
        Random random = new Random(randomSeed);
        System.out.println(gymData.get(random.nextInt(gymData.size())).trim());
    }

    void printAllStatistics () {
        for (Statistics statistic : Statistics.values()) {
            printStatistic(statistic);
        }
    }

    void printStatistic ( Statistics stats ) {
        switch (stats) {
            case POWER_TRAINING: {
                System.out.println(powerStatistics);
                break;
            }
            case CARDIO_TRAINING: {
                System.out.println(cardioStatistics);
                break;
            }
            case TRAININGS: {
                try {
                    System.out.println(new DateStatisticsHandler().readDateAndTimeStatistics(PATH));
                } catch (IOException e) {
                    System.out.println("Cannot read " + PATH + " as a data file.");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    void train () {
        if (trainings == null) {
            System.out.println("It's bad.");
        } else if (!trainings.isEmpty() && trainings.remove().isCardioTraining()) {
            cardioStatistics += 1;
            DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
            dateStatisticsHandler.saveTrainingDateAndTimeStatistics(PATH);
            System.out.println("Doing cardio training");
        } else if (!trainings.isEmpty()) {
            powerStatistics += 1;
            DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
            dateStatisticsHandler.saveTrainingDateAndTimeStatistics(PATH);
            System.out.println("Doing power training");
        } else {
            System.out.println("There is no trainings available. Ask your Trainer for training plan.");
        }
    }

    //Ta metoda nie zależy od płci. Żeby pisać obiektowo przydałoby się to jednak zmienić.
    // Np podobne wywyołanie metody dla chooseOtherTrainingPlan zakończyłoby się klęską :)
    void chooseOtherTrainer () {
        MaleSportsMan maleSportsMan = new MaleSportsMan(this);
        maleSportsMan.chooseTrainer();
        myTrainer=maleSportsMan.getSportsMan().getMyTrainer();
    }
}
