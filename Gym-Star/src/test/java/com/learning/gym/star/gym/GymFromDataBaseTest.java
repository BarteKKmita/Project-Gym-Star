package com.learning.gym.star.gym;

import com.learning.gym.star.gym.database.GymFromDataBaseJpa;
import com.learning.gym.star.gym.database.GymQueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GymFromDataBaseTest {
    private GymQueryParameters gymQueryParameters = new GymQueryParameters();
    @Autowired
    private DataSource dataSource;


    @Test
    void testJdbcTemplateCreation () {
        assertNotNull(dataSource);
    }

    @Test
    void shouldReturnGymData () {
        //Given
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        GymQueryParameters gymQueryParameters = new GymQueryParameters();
        GymFromDataBaseJpa gymFromDataBase = new GymFromDataBaseJpa(jdbcTemplate, gymQueryParameters);
        int expectedColumns = 5;
        int gym_id = 1;
        //When
        String[] gymData = gymFromDataBase.getGymDataById(gym_id);
        //Then
        assertEquals(expectedColumns, gymData.length);
    }

    @Test
    void shouldThrowExceptionWhenCallingNotExistingGymRecord () {
        //Given
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        GymQueryParameters gymQueryParameters = new GymQueryParameters();
        GymFromDataBaseJpa gymFromDataBase = new GymFromDataBaseJpa(jdbcTemplate, gymQueryParameters);
        int gym_id = 6;
        //Then
        assertThrows(EmptyResultDataAccessException.class, () -> gymFromDataBase.getGymDataById(gym_id));
    }

    @Test
    void shouldAddRecordToGymTable () {
        //Given
        Gym testGym = Gym.builder().gymId("2")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        GymFromDataBaseJpa gymFromDataBaseJpa = new GymFromDataBaseJpa(new JdbcTemplate(dataSource), gymQueryParameters);
        //Then
        assertDoesNotThrow(() -> gymFromDataBaseJpa.add(testGym));

    }

    @Test
    void shouldThrowExceptionWhenAddingRecordWithExistingGymId () {
        //Given
        Gym testGym = Gym.builder().gymId("1")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        GymFromDataBaseJpa gymFromDataBaseJpa = new GymFromDataBaseJpa(new JdbcTemplate(dataSource), gymQueryParameters);
        //Then
        assertThrows(DuplicateKeyException.class, () -> gymFromDataBaseJpa.add(testGym));
    }

    @Test
    void shouldUpdateExistingGym () {
        //Given
        Gym testGym = Gym.builder().gymId("1")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        int gym_id = 1;
        GymFromDataBaseJpa gymFromDataBaseJpa = new GymFromDataBaseJpa(new JdbcTemplate(dataSource), gymQueryParameters);
        //Then
        assertDoesNotThrow(() -> gymFromDataBaseJpa.update(testGym, gym_id));
    }

    @Test
    void shouldDeleteExistingGym () {
        //Given
        int gym_id = 1;
        GymFromDataBaseJpa gymFromDataBaseJpa = new GymFromDataBaseJpa(new JdbcTemplate(dataSource), gymQueryParameters);
        //Then
        assertDoesNotThrow(() -> gymFromDataBaseJpa.delete(gym_id));
    }

    //TODO
    //Write test cases for reading data from embedded database after specified CRUD transactions
}