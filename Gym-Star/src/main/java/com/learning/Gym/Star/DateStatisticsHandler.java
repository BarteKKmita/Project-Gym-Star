package com.learning.Gym.Star;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateStatisticsHandler {

    void saveTrainingDateAndTimeStatistics ( String path ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String[] dateAndHourOfTraining = dateFormat.format(date).split(" ");
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
                FileWriter writeDateData = new FileWriter(f, true);
                writeDateData.append("Data, Godzina")
                        .append("\n").append(dateAndHourOfTraining[0]).append(",").append(dateAndHourOfTraining[1])
                        .append("\n");
                writeDateData.flush();
                writeDateData.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter writeDateData = new FileWriter(f, true);
                writeDateData.append((dateAndHourOfTraining[0])).append(",").append(dateAndHourOfTraining[1]).append("\n");
                writeDateData.flush();
                writeDateData.close();
            } catch (IOException e) {
                System.out.println("Path to folder do not exist or can not be reached");
                e.printStackTrace();
            }
        }
    }

    String readDateAndTimeStatistics ( String path ) throws IOException {
        File csvData = new File(path);
        CSVParser parser = null;
        StringBuilder statistics = new StringBuilder();
        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.EXCEL);//by adding CSVFormat.EXCEL.withHeader() - listing without header
        for (CSVRecord record : parser) {
            statistics.append(record.get(0)).append(" ").append(record.get(1)).append(" \n");
        }
        return statistics.toString().trim();
    }
}
