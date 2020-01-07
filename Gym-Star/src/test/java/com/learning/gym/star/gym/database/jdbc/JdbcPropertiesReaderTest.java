package com.learning.gym.star.gym.database.jdbc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcPropertiesReaderTest {

    @Test
    void shouldReturnDatabaseProperty () {
        //Given
        String expectedPassword = "Some_secret_password";
        JdbcPropertiesReader jdbcPropertiesReader = new JdbcPropertiesReader("data/PropertiesTest.txt");
        //When
        String property = jdbcPropertiesReader.getPassword();
        //Then
        assertEquals(expectedPassword, property);
    }
}