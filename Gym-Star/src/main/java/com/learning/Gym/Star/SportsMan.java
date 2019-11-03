package com.learning.Gym.Star;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

abstract class SportsMan implements Gender {
    private final String name;
    private final String surname;
    private final GenderChoose gender;
    private Trainer myTrainer;
    private Queue <TrainingType> trainings;

    protected SportsMan ( String name, String surname, GenderChoose gender ) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
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
        System.out.println("Enter trainer name");
        Scanner enterTrainerName = new Scanner(System.in);
        String chosenName = enterTrainerName.nextLine();
        System.out.println("Enter trainer surname");
        String chosenSurname = enterTrainerName.nextLine();
        for (Trainer trainer : listOfTrainers) {
            //    && trainer.getSurname().equals(chosenSurname)
            if (trainer.getName().equals(chosenName)) {
                myTrainer = trainer;
            }
        }
        if (myTrainer == null) {
            System.out.println("There is no trainer with such name and surname");
        }
    }

    //Helper method to simulate SQL response
    private List <Trainer> getTrainers () {
        ListOfTrainers trainers = new ListOfTrainers();
        return trainers.getListOfTrainers();
    }

    void chooseOtherTrainer () {
        List <Trainer> listOfTrainers = getTrainers();
        myTrainer = null;
        chooseTrainer();
    }

    void train () {

        if(!trainings.isEmpty()&&trainings.remove().isCardioTraining()){
            //Kod dla statystyk cardio
            saveTrainingDateAndTimeStatistics();
        }else if(!trainings.isEmpty()){
            //kod dla statystyk power
            saveTrainingDateAndTimeStatistics();
        }else{
            System.out.println("There is no trainings available. Ask your Trainer for training plan.");
        }
    }

    private void saveTrainingDateAndTimeStatistics () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String[] dateAndHourOfTraining = dateFormat.format(date).split(" ");
        File f = new File("data\\Training Days.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
                FileWriter writeDateData = new FileWriter(f,true);
                writeDateData.append("Data, Godzina");
                writeDateData.append("\n");
                writeDateData.append(dateAndHourOfTraining[0]).append(",").append(dateAndHourOfTraining[1]);
                writeDateData.append("\n");
                writeDateData.flush();
                writeDateData.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FileWriter writeDateData = new FileWriter(f,true);
                writeDateData.append((dateAndHourOfTraining[0])).append(",").append(dateAndHourOfTraining[1]).append("\n");
                writeDateData.flush();
                writeDateData.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    abstract void printAllStatistics ();

    abstract void printStatistic ( Statistics stats );

    abstract void showClosestGym ( int randomSeed );


    void getTrainingPlan () {
        trainings = myTrainer.preparePlan(this);
    }

}
