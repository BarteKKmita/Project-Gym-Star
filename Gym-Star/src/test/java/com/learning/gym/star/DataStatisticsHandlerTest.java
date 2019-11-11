package com.learning.gym.star;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
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
        String PATH = "data\\FileNotExisting";
        //Then
        assertThrows(FileNotFoundException.class, () -> dateStatisticsHandler.readDateAndTimeStatistics(PATH));
    }

    @Test
    void shouldReturnStringWihtDataTypeWhenCallingExistingFile () {
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
        String PATH = "data\\StatisticsReaderTest.txt";
        int numberOfLinesBefore = 0;
        //When
        numberOfLinesBefore = linesInFile(PATH);
        dateStatisticsHandler.saveTrainingDateAndTimeStatistics(PATH);
        //Then
        int numberOfLinesAfter = linesInFile(PATH);
        assertEquals(numberOfLinesBefore + 1, numberOfLinesAfter);
    }

    private int linesInFile ( String PATH ) {
        int numberOfLinesBefore = 0;
        File csvData = new File(PATH);
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.EXCEL);//by adding CSVFormat.EXCEL.withHeader() - listing without header
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (CSVRecord record : parser) {
            numberOfLinesBefore += 1;
        }
        return numberOfLinesBefore;
    }

    @Test
    void shouldBeAdded2LinesWhenCallingNotExistingFile () {
        //Given
        DateStatisticsHandler dateStatisticsHandler = new DateStatisticsHandler();
        String PATH = "data\\NotExistingFileYet.txt";
        //When
        dateStatisticsHandler.saveTrainingDateAndTimeStatistics(PATH);
        int numberOfLinesAfter = linesInFile(PATH);
        //Then
        assertEquals(2, numberOfLinesAfter);
    }
}
