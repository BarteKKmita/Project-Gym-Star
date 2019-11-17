package com.learning.gym.star;
import com.learning.gym.star.sportsman.ConcreteSportsMan;
import com.learning.gym.star.sportsman.GenderChoose;
import com.learning.gym.star.sportsman.SportsMan;
import com.learning.gym.star.statistics.DateStatisticsHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataStatisticsHandlerTest {

    private SportsMan sportsMan;

    @BeforeEach
    void initListOfTrainers () {
        sportsMan = new ConcreteSportsMan("Test", "SportsMan", GenderChoose.M);
    }

    @Test
    void shouldThrowIOExceptionWhenCallingNotExistingFile () {
        //Given
        DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
        String path = "data\\FileNotExisting";
        //Then
        assertThrows(FileNotFoundException.class, () -> dateStatisticsHandler.readDateAndTimeStatistics(path));
    }

    @Test
    void shouldReturnStringWithDataTypeWhenCallingExistingFile () {
        //Given
        DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
        String PATH = "data\\StatisticsReaderTest.txt";
        //When
        ByteArrayInputStream testIn;
        String data = "Mariusz";
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        sportsMan.chooseTrainer();
        sportsMan.getTrainingPlan(3);
        sportsMan.train();
        try {
            String[] methodOutput = dateStatisticsHandler.readDateAndTimeStatistics(PATH).split(" ");
            String output = (methodOutput[methodOutput.length - 2] + " " + methodOutput[methodOutput.length - 1]).trim();
            //Then
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date.parse(output);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void shouldBeAddedLineInFileWhenCallingExistingFile () {
        //Given
        DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
        String path = "data\\StatisticsReaderTest.txt";
        long numberOfLinesBefore = 0;
        long numberOfLinesAfter= 0;
        Path file = Paths.get(path);
        try {
            numberOfLinesBefore = Files.lines(file).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //When
        dateStatisticsHandler.saveTrainingDateAndTimeStatistics(path);
        try {
            numberOfLinesAfter = Files.lines(file).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Then
        assertEquals(numberOfLinesBefore + 1, numberOfLinesAfter);
    }

    @Test
    void shouldBeAdded2LinesWhenCallingNotExistingFile () {
        //Given
        DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
        String path = "data\\NotExistingFileYet.txt";
        long numberOfLinesAfter=0;
        Path file = Paths.get(path);
        File checkFilePresents = file.toFile();
        if(checkFilePresents.exists()){
            checkFilePresents.delete();
        }
        //When
        dateStatisticsHandler.saveTrainingDateAndTimeStatistics(path);
        try {
            numberOfLinesAfter = Files.lines(file).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Then
        assertEquals(2, numberOfLinesAfter);
    }
}
