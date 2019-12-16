package com.learning.gym.star.gym;

import lombok.AllArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@AllArgsConstructor
public class JdbcPropertiesReader {

    private String pathToFile;
    private final static String URL = "spring.datasource.url";
    private final static String USERNAME = "spring.datasource.username";
    private final static String PASSWORD = "spring.datasource.password";

    public JdbcPropertiesReader () {
        pathToFile = "src/main/resources/application.properties";
    }

    String[] getDatabaseProperties () {
        String[] properties = new String[3];
        try (InputStream input = new FileInputStream(pathToFile)) {
            Properties propertiesReader = new Properties();
            propertiesReader.load(input);
            properties[0] = propertiesReader.getProperty(URL);
            properties[1] = propertiesReader.getProperty(USERNAME);
            properties[2] = propertiesReader.getProperty(PASSWORD);
        } catch (IOException ex) {
            System.out.println("File cannot be opened or not exists");
            ex.printStackTrace();
        }
        return properties;
    }
}


