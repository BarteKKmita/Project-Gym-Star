package com.learning.gym.star.training.power.database;

import com.learning.gym.star.EmbeddedMySqlProvider;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PowerTrainingJpaRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PowerTrainingRepository repository;

    @BeforeAll
    public static void setUpClass(){
        EmbeddedMySqlProvider.setUpClass();
    }

    @AfterAll
    public static void tearDownClass(){
        EmbeddedMySqlProvider.tearDownClass();
    }

    @Test
    public void shouldNotBeNull(){
        assertNotNull(repository);
        assertNotNull(dataSource);
    }

    @Test
    public void shouldReturnPowerEntity(){
        //Given
        var powerTrainingId = "1";
        //When
        boolean isPowerTrainingExists = repository.existsById(powerTrainingId);
        //Then
        assertTrue(isPowerTrainingExists);
    }

    @Test
    public void shouldUpdatePowerTrainingCounter(){
        //Given
        var powerTrainingId = "1";
        int expectedTrainingCount = 6;
        //When
        repository.updateTrainingCounter(powerTrainingId);
        int actualTrainingCount = repository.findById(powerTrainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldResetPowerTrainingCounter(){
        //Given
        var powerTrainingId = "1";
        int expectedTrainingCount = 0;
        //When
        repository.resetPowerStatistics(powerTrainingId);
        int actualTrainingCount = repository.findById(powerTrainingId).get().getTrainingCount();
        //Then
        assertEquals(expectedTrainingCount, actualTrainingCount);
    }

    @Test
    public void shouldCreatePowerTrainingEntity(){
        //Given
        var newPowerId = repository.saveAndFlush(new PowerTrainingEntity()).getPowerId();
        //When
        boolean isPowerStatsCreated = repository.existsById(newPowerId);
        //Then
        assertTrue(isPowerStatsCreated);
    }
}
