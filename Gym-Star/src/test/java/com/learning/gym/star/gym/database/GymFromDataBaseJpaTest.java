package com.learning.gym.star.gym.database;

import com.learning.gym.star.gym.Gym;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test case class can be only run by TestSuite class. In TestSuite class is included all needed configuration to
 * run it with spring and MySQL embedded database.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GymFromDataBaseJpaTest {
    @Autowired
    @Resource
    private DataSource dataSource;

    private GymQueryParameters gymQueryParameters = new GymQueryParameters();
    private static JdbcTemplate jdbcTemplate;
    private GymFromDataBaseJpa gymFromDataBaseJpa;

    @Before
    public void before () {
        jdbcTemplate = new JdbcTemplate(dataSource);
        gymFromDataBaseJpa = new GymFromDataBaseJpa(jdbcTemplate, gymQueryParameters);
    }

    @Test
    public void testJdbcTemplateCreation () {
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void shouldGetAllGymData () {
        //Given
        int expectedSize = 2;
        //When
        int gymDataSize = gymFromDataBaseJpa.getGymData().size();
        //Then
        assertEquals(expectedSize, gymDataSize);
    }

    @Test
    public void shouldGetGymDataByGymId () {
        //Given
        int gymId = 1;
        int expectedSize = 5;
        String expectedName = "pierwsza";
        String expectedStreet = "sezamkowa";
        String expectedCity = "rudy";
        String expectedBuildingNumber = "102";
        //When
        String[] gymDataById = gymFromDataBaseJpa.getGymDataById(gymId);
        //Then
        assertEquals(expectedSize, gymDataById.length);
        assertEquals(expectedName, gymDataById[1]);
        assertEquals(expectedStreet, gymDataById[2]);
        assertEquals(expectedCity, gymDataById[3]);
        assertEquals(expectedBuildingNumber, gymDataById[4]);
    }

    @Test
    public void shouldAddRecord () {
        //Given
        int gymId = 6;
        int expectedLength = 5;
        Gym gym = Gym.builder().gymId("6")
                .gymName("TestName")
                .buildingNumber("60")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJpa.add(gym);
        //Then
        assertEquals(expectedLength, gymFromDataBaseJpa.getGymDataById(gymId).length);
    }

    @After
    public void removeAddedRecordForTestsControl () {
        gymFromDataBaseJpa.delete(6);
    }

    @Test
    public void shouldThrowExceptionWhenAddingRecordWithoutAllData () {
        //Given
        Gym gym = Gym.builder().gymId("6")
                .gymName("TestName")
                .buildingNumber("1")
                .city("TestCity")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJpa.add(gym));
    }

    @Test
    public void shouldNotAddRecordWithEmptyData () {
        //Given
        Gym gym = Gym.builder().gymId("6")
                .gymName("")
                .buildingNumber("1")
                .city("")
                .city("")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJpa.add(gym));
    }

    @Test
    public void shouldUpdateRecord () {
        //Given
        int gymId = 2;
        String expectedGymName = "UpdateTest";
        Gym gym = Gym.builder().gymId("2")
                .gymName(expectedGymName)
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJpa.update(gym, gymId);
        String[] gymDataById = gymFromDataBaseJpa.getGymDataById(gymId);
        //Then
        assertEquals(expectedGymName, gymDataById[1]);
    }

    @After
    public void revertUpdateForTestsControl () {
        int gymId = 2;
        String expectedGymName = "pierwsza";
        Gym gym = Gym.builder().gymId("2")
                .gymName(expectedGymName)
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        gymFromDataBaseJpa.update(gym, gymId);
    }

    @Test
    public void shouldNotUpdateRecordWithoutAllData () {
        //Given
        int gymId = 1;
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJpa.update(gym, gymId));
    }

    @Test
    public void shouldNotUpdateRecordWhenWrongId () {
        //Given
        int gymId = 7;
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJpa.update(gym, gymId);
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJpa.getGymDataById(gymId));
    }

    @Test
    public void shouldRemoveRecord () {
        //Given
        int gymIdToDelete = 2;
        int expectedDataLength = 1;
        //When
        gymFromDataBaseJpa.delete(gymIdToDelete);
        List <String> gymData = gymFromDataBaseJpa.getGymData();
        //Then
        assertEquals(expectedDataLength, gymData.size());
    }

    @Test
    public void shouldThrowExceptionWhenCallingNotExistingGymRecord () {
        //Given
        int gym_id = 6;
        //Then
        assertThrows(EmptyResultDataAccessException.class, () -> gymFromDataBaseJpa.getGymDataById(gym_id));
    }

    @Test
    public void shouldThrowExceptionWhenAddingRecordWithExistingGymId () {
        //Given
        Gym testGym = Gym.builder().gymId("1")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        //Then
        assertThrows(DuplicateKeyException.class, () -> gymFromDataBaseJpa.add(testGym));
    }
}