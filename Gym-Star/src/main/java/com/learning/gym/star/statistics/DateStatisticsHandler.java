package com.learning.gym.star.statistics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStatisticsHandler {
    private static final int DATE_INDEX = 0;
    private static final int TIME_INDEX = 1;

    public void saveTrainingDateAndTimeStatistics ( String pathToStatisticsFile ) {
        File statisticsFile = new File(pathToStatisticsFile);
        if (!statisticsFile.exists()) {
            try(FileWriter fileWriter = new FileWriter(statisticsFile, true)) {
                statisticsFile.createNewFile();
                writeStatisticsToFile(fileWriter, true);
            } catch (IOException e) {
                System.out.println("File " + pathToStatisticsFile + "could not be created or cannot close opened file.");
                e.printStackTrace();
            }
        } else {
            try( FileWriter fileWriter = new FileWriter(statisticsFile, true)) {
                writeStatisticsToFile(fileWriter, false);
            } catch (IOException e) {
                System.out.println("Path to folder or file do not exist or can not be reached. Path: " + pathToStatisticsFile);
                e.printStackTrace();
            }
        }
    }

    public String readDateAndTimeStatistics ( String pathToStatisticsFile ) throws IOException {
        File csvData = new File(pathToStatisticsFile);
        if (!csvData.exists()) {
            throw new FileNotFoundException();
        }
        CSVParser parser;
        StringBuilder statistics = new StringBuilder();
        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.EXCEL);//by adding CSVFormat.EXCEL.withHeader() - listing without header
        for (CSVRecord record : parser) {
            statistics.append(record.get(DATE_INDEX))
                    .append(" ")
                    .append(record.get(TIME_INDEX))
                    .append(" \n");
        }
        return statistics.toString().trim();
    }

    private void writeStatisticsToFile ( FileWriter fileWriter, boolean withHeader ) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String[] dateAndHourOfTraining = dateFormat.format(date).split(" ");
        if (withHeader) {
            fileWriter.append("Date, Time");
        }
        fileWriter.append("\n")
                .append(dateAndHourOfTraining[DATE_INDEX])
                .append(",")
                .append(dateAndHourOfTraining[TIME_INDEX]);
        fileWriter.flush();
    }
}
