package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GymFromDataBaseJdbcSpringTest {

    @Resource
    private DataSource dataSource;

    private GymQueryParameters gymQueryParameters = new GymQueryParameters();
    private static JdbcTemplate jdbcTemplate;
    private GymFromDataBaseJdbcSpring gymFromDataBaseJdbcSpring;

    @BeforeAll
    public void setUpClass(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        gymFromDataBaseJdbcSpring = new GymFromDataBaseJdbcSpring(jdbcTemplate, gymQueryParameters);
    }

    @Test
    public void testJdbcTemplateCreation(){
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void shouldGetAllGymData(){
        //Given
        int expectedSize = 3;
        //When
        int gymDataSize = gymFromDataBaseJdbcSpring.getGymData().size();
        //Then
        assertEquals(expectedSize, gymDataSize);
    }

    @Test
    public void shouldGetGymDataByGymId(){
        //Given
        int gymId = 1;
        int expectedSize = 5;
        String expectedName = "pierwsza";
        String expectedStreet = "sezamkowa";
        String expectedCity = "rudy";
        String expectedBuildingNumber = "102";
        //When
        String[] gymDataById = gymFromDataBaseJdbcSpring.getGymDataById(gymId);
        //Then
        assertEquals(expectedSize, gymDataById.length);
        assertEquals(expectedName, gymDataById[1]);
        assertEquals(expectedStreet, gymDataById[2]);
        assertEquals(expectedCity, gymDataById[3]);
        assertEquals(expectedBuildingNumber, gymDataById[4]);
    }

    @Test
    public void shouldAddRecord(){
        //Given
        int gymId = 2;
        int expectedLength = 5;
        Gym gym = Gym.builder().gymId("2")
                .gymName("TestName")
                .buildingNumber("60")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJdbcSpring.add(gym);
        //Then
        assertEquals(expectedLength, gymFromDataBaseJdbcSpring.getGymDataById(gymId).length);
    }

    @Test
    public void shouldThrowExceptionWhenAddingRecordWithoutAllData(){
        //Given
        Gym gym = Gym.builder().gymId("6")
                .gymName("TestName")
                .buildingNumber("1")
                .city("TestCity")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJdbcSpring.add(gym));
    }

    @Test
    public void shouldNotAddRecordWithEmptyData(){
        //Given
        Gym gym = Gym.builder().gymId("6")
                .gymName("")
                .buildingNumber("1")
                .city("")
                .city("")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJdbcSpring.add(gym));
    }

    @Test
    public void shouldUpdateRecord(){
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
        gymFromDataBaseJdbcSpring.update(gym, gymId);
        String[] gymDataById = gymFromDataBaseJdbcSpring.getGymDataById(gymId);
        //Then
        assertEquals(expectedGymName, gymDataById[1]);
    }

    @Test
    public void shouldNotUpdateRecordWithoutAllData(){
        //Given
        int gymId = 1;
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .build();
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJdbcSpring.update(gym, gymId));
    }

    @Test
    public void shouldNotUpdateRecordWhenWrongId(){
        //Given
        int gymId = 7;
        Gym gym = Gym.builder().gymId("1")
                .gymName("TestUpdate")
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        //When
        gymFromDataBaseJdbcSpring.update(gym, gymId);
        //Then
        assertThrows(NonTransientDataAccessException.class, () -> gymFromDataBaseJdbcSpring.getGymDataById(gymId));
    }

    @Test
    public void shouldRemoveRecord(){
        //Given
        int gymIdToDelete = 3;
        int expectedDataLength = 3;
        //When
        gymFromDataBaseJdbcSpring.delete(gymIdToDelete);
        List<String> gymData = gymFromDataBaseJdbcSpring.getGymData();
        //Then
        assertEquals(expectedDataLength, gymData.size());
    }

    @Test
    public void shouldThrowExceptionWhenCallingNotExistingGymRecord(){
        //Given
        int notExistingGymId = 20;
        //Then
        assertThrows(EmptyResultDataAccessException.class, () -> gymFromDataBaseJdbcSpring.getGymDataById(notExistingGymId));
    }

    @Test
    public void shouldThrowExceptionWhenAddingRecordWithExistingGymId(){
        //Given
        Gym testGym = Gym.builder().gymId("1")
                .gymName("Test Gym")
                .city("Test city")
                .buildingNumber("1")
                .street("Sezamkowa")
                .build();
        //Then
        assertThrows(DuplicateKeyException.class, () -> gymFromDataBaseJdbcSpring.add(testGym));
    }

    @AfterAll
    public void tearDown(){
        gymFromDataBaseJdbcSpring.delete(6);
        int gymId = 2;
        String expectedGymName = "pierwsza";
        Gym gym = Gym.builder().gymId("2")
                .gymName(expectedGymName)
                .buildingNumber("51")
                .street("Sezamkowa")
                .city("TestCity")
                .build();
        gymFromDataBaseJdbcSpring.update(gym, gymId);
    }
}
