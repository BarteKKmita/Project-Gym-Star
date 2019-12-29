package com.learning.gym.star.gym.database;

import com.learning.gym.star.gym.Gym;
import com.wix.mysql.EmbeddedMysql;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * By this I want to test connection with database only with usage of Java without any external framework (like Spring).
 */

class GymFromDataBaseJDBCTest{

    private static EmbeddedMysql embeddedMysql;
    private static JdbcPropertiesReader propertiesReader = new JdbcPropertiesReader("src/main/resources/application-test.properties");
    private JdbcConnector jdbcConnector = new JdbcConnector(propertiesReader);
    private GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC(jdbcConnector);

    @BeforeAll
    private static void setupBeforeClass(){
        TestSuite.setupBeforeClass();
    }

    @AfterAll
    private static void tearDownAfterClass(){
        if(null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }

    @Test
    void shouldGetAllGymData(){
        //Given
        int expectedDataLength = 3;
        //When
        int gymDataSize = gymFromDataBaseJDBC.getGymData().size();
        //Then
        assertEquals(expectedDataLength, gymDataSize);
    }

    @Test
    void shouldGetGymDataFromGym_id(){
        //Given
        int gymId = 1;
        int expectedSize = 5;
        String expectedName = "pierwsza";
        //When
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertEquals(expectedSize, gymDataById.length);
        assertEquals(expectedName, gymDataById[1]);
    }

    @Test
    void shouldAddRecord(){
        //Given
        int gymId = 6;
        Gym gym = Gym.builder().gymId("6")
                .gymName("TestName")
                .buildingNumber("60")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        int expectedLength = 5;
        //When
        gymFromDataBaseJDBC.add(gym);
        //Then
        assertEquals(expectedLength, gymFromDataBaseJDBC.getGymDataById(gymId).length);
    }

    @Test
    void shouldNotAddRecordWithoutAllData(){
        //Given
        int gymId = 6;
        Gym gym = Gym.builder().gymId("6")
                .gymName("TestName")
                .buildingNumber("1")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJDBC.add(gym);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertNull(gymDataById);
    }

    @Test
    void shouldNotAddRecordWithEmptyData(){
        //Given
        int gymId = 7;
        Gym gym = Gym.builder().gymId("6")
                .gymName("")
                .buildingNumber("1")
                .city("")
                .city("")
                .build();
        //When
        gymFromDataBaseJDBC.add(gym);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertNull(gymDataById);
    }

    @Test
    void shouldUpdateRecord(){
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
        gymFromDataBaseJDBC.update(gym, gymId);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertEquals(expectedGymName, gymDataById[1]);
    }

    @Test
    void shouldNotUpdateRecordWithoutAllData(){
        //Given
        int gymId = 1;
        String expectedGymName = "pierwsza";
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .build();
        //When
        gymFromDataBaseJDBC.update(gym, gymId);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertEquals(expectedGymName, gymDataById[1]);
    }

    @Test
    void shouldNotUpdateRecordWhenWrongId(){
        //Given
        int gymId = 7;
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJDBC.update(gym, gymId);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertNull(gymDataById);
    }

    @Test
    void shouldRemoveRecord(){
        //Given
        int gymIdToDelete = 2;
        int expectedDataLength = 2;
        //When
        gymFromDataBaseJDBC.delete(gymIdToDelete);
        List <String> gymData = gymFromDataBaseJDBC.getGymData();
        //Then
        assertEquals(expectedDataLength, gymData.size());
    }

    @Test
    void shouldNotRemoveRecordWhenNotExistingRecord(){
        //Given
        int gymIdToDelete = 4;
        int expectedDataLength = 2;
        //When
        gymFromDataBaseJDBC.delete(gymIdToDelete);
        List <String> gymData = gymFromDataBaseJDBC.getGymData();
        //Then
        assertEquals(expectedDataLength, gymData.size());
    }

    @Test
    void shouldThrowExceptionWhenAddingRecordWithExistingGymId(){
        //Given
        String expectedGymName = "pierwsza";
        int gymId = 1;
        Gym testGym = Gym.builder().gymId("1")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        //When
        gymFromDataBaseJDBC.add(testGym);
        String[] gymDataById = gymFromDataBaseJDBC.getGymDataById(gymId);
        //Then
        assertEquals(expectedGymName, gymDataById[1]);
    }
}