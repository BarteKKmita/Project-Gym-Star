package com.learning.Gym.Star;

import java.io.IOException;
import java.util.*;

class SportsMan implements Gender {
    private final String name;
    private final String surname;
    private final GenderChoose gender;
    private Trainer myTrainer;
    private Queue <Training> trainings;
    private final String PATH;
    private int cardioStatistics = 0;
    private int powerStatistics = 0;

    SportsMan ( String name, String surname, GenderChoose gender ) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        PATH = "data\\" + name + surname;
    }

    public String getName () {
        return name;
    }

    public String getSurname () {
        return surname;
    }

    @Override
    public String getGender () {
        return gender.getGender();
    }

    public Trainer getMyTrainer () {
        return myTrainer;
    }

    void chooseTrainer () {
        List <Trainer> listOfTrainers = getTrainers();
        System.out.println("Available trainers list: ");
        for (Trainer trainer : listOfTrainers) {
            System.out.println(trainer.getName() + " " + trainer.getSurname());
        }
        System.out.println("Choose trainer you want to train with");
        String chosenName = getTrainerData("name");
        //String chosenSurname= getTrainerData("surname");
        for (Trainer trainer : listOfTrainers) {
            //    && trainer.getSurname().equals(chosenSurname)
            if (trainer.getName().equals(chosenName)) {
                myTrainer = trainer;
            }
        }
        if (myTrainer == null) {
            System.out.println("There is no trainer with such name and surname.\n " +
                    " You were given trainer by default");
            myTrainer = listOfTrainers.get(0);
        }
    }

    private String getTrainerData ( String name ) {
        System.out.println("Enter trainer " + name);
        Scanner enterTrainerName = new Scanner(System.in);
        return enterTrainerName.nextLine();
    }

    /**
     * Helper method to simulate SQL response
     */

    private List <Trainer> getTrainers () {
        ListOfTrainers trainers = new ListOfTrainers();
        return trainers.getListOfTrainers();
    }

    void chooseOtherTrainer () {
        myTrainer = null;
        chooseTrainer();
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

    void showClosestGym ( int randomSeed ) {
        GymDataHandler gymDataHandler = new GymDataHandler();
        List <String> gymData = gymDataHandler.getGymData();
        Random random = new Random(randomSeed);
        System.out.println(gymData.get(random.nextInt(gymData.size())).trim());
    }

    void getTrainingPlan ( int trainingDays ) {
        trainings = myTrainer.preparePlan(this, trainingDays);
    }

}
