package com.learning.gym.star.gym;

import com.learning.gym.star.gym.database.JdbcPropertiesReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcPropertiesReaderTest {

    @Test
    void shouldReturnDatabaseProperties () {
        //Given
        int expectedLength = 3;
        JdbcPropertiesReader jdbcPropertiesReader = new JdbcPropertiesReader();
        //When
        String[] properties = jdbcPropertiesReader.getDatabaseProperties();
        //Then
        assertEquals(expectedLength, properties.length);
    }
}