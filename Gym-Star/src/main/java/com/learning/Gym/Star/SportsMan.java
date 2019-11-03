package com.learning.Gym.Star;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

abstract class SportsMan implements Gender {
    private final String name;
    private final String surname;
    private final GenderChoose gender;
    private Trainer myTrainer;
    private Queue <TrainingType> trainings;
    private final String PATH = "data\\Training Days.txt";

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
      //  System.out.println("Enter trainer surname");
       // String chosenSurname = enterTrainerName.nextLine();
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

    /**
     *  Helper method to simulate SQL response
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
        if(trainings==null){
            System.out.println("Jest źle");
        }
        if(!trainings.isEmpty()&&trainings.remove().isCardioTraining()){
            //TODO
            //Kod dla statystyk cardio
            saveTrainingDateAndTimeStatistics();
            System.out.println("Doing cardio training");
        }else if(!trainings.isEmpty()){
            //TODO
            //kod dla statystyk power
            saveTrainingDateAndTimeStatistics();
            System.out.println("Doing power training");
        }else{
            System.out.println("There is no trainings available. Ask your Trainer for training plan.");
        }
    }

    private void saveTrainingDateAndTimeStatistics () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String[] dateAndHourOfTraining = dateFormat.format(date).split(" ");
        File f = new File(PATH);
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

    void printAllStatistics (){
        for (Statistics statistic:Statistics.values()){
        printStatistic(statistic);
        }
    }

    void printStatistic ( Statistics stats ){
        switch (stats){
            case POWER_TRAINING:{
                //TODO
                //Print power statistics
                break;
            }
            case CARDIO_TRAINING:{
                //TODO
                //Print cardop statistics
                break;
            }
            case TRAININGS:{
                readDateAndTimeStatistics();
                break;
            }
        }
    }

    private void readDateAndTimeStatistics () {
        File csvData = new File(PATH);
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(csvData, Charset.defaultCharset() , CSVFormat.EXCEL); //by adding CSVFormat.EXCEL.withHeader() listing without header
        } catch (IOException e) {
            System.out.println("Cannot read " + PATH + " as a data file.");
            e.printStackTrace();
        }
        for (CSVRecord record : parser) {
            System.out.println(record.get(0)+" "+record.get(1));
        }
    }

    void showClosestGym ( int randomSeed ){
        //TODO
        Gym gym = new Gym();
        List <String> gymData = gym.getGymData();
        Random random = new Random(randomSeed);
        System.out.println(gymData.get(random.nextInt(gymData.size())).trim());
    }


    void getTrainingPlan (int trainingDays) {

        trainings = myTrainer.preparePlan(this, trainingDays);
    }

}
