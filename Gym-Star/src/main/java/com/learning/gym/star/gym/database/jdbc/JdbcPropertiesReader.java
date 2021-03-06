package com.learning.gym.star.gym.database.jdbc;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RequiredArgsConstructor
final class JdbcPropertiesReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcPropertiesReader.class);
    private final String pathToFile;
    private final static String URL = "spring.datasource.url";
    private final static String USERNAME = "spring.datasource.username";
    private final static String PASSWORD = "spring.datasource.password";

    JdbcPropertiesReader(){
        pathToFile = "src/main/resources/application2.properties";
    }

    String getURL(){
        return getDatabaseProperties(URL);
    }

    String getUsername(){
        return getDatabaseProperties(USERNAME);
    }

    public String getPassword(){
        return getDatabaseProperties(PASSWORD);
    }

    private String getDatabaseProperties(String property){
        String readProperty = "";
        try (InputStream input = new FileInputStream(pathToFile)) {
            Properties propertiesReader = new Properties();
            propertiesReader.load(input);
            readProperty = propertiesReader.getProperty(property);
        } catch (IOException ex) {
            LOGGER.error("File cannot be opened or not exists");
            ex.printStackTrace();
            System.exit(1);
        }
        return readProperty;
    }
}


