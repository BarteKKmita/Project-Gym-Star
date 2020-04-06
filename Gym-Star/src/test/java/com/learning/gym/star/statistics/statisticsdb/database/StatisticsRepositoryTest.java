package com.learning.gym.star.statistics.statisticsdb.database;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.cardio.database.CardioTrainingJpaRepository;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import com.learning.gym.star.training.power.database.PowerTrainingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StatisticsRepositoryTest {

    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private PowerTrainingRepository powerTrainingRepository;
    @Autowired
    private CardioTrainingJpaRepository cardioTrainingRepository;

    @Test
    public void shouldNotBeNull(){
        assertNotNull(statisticsRepository);
    }

    @Test
    private void shouldGetStatisticsById(){
        //Given
        var statisticsId = "1";
        //when
        boolean isStatisticsExists = statisticsRepository.findById(statisticsId).isPresent();
        //Then
        assertTrue(isStatisticsExists);
    }

    @Test
    public void shouldAddStatisticsEntity(){
        //Given
        var testStatisticsEntity = getTestStatisticsEntity();
        var expectedCardioRecords = cardioTrainingRepository.findAll().size() + 1;
        var expectedPowerRecords = powerTrainingRepository.findAll().size() + 1;
        //When
        var statisticsEntity = statisticsRepository.saveAndFlush(testStatisticsEntity);
        int actualCardioRecordsSize = cardioTrainingRepository.findAll().size();
        int actualPowerRecordsSize = powerTrainingRepository.findAll().size();
        boolean isNewStatisticsExists = statisticsRepository.existsById(statisticsEntity.getStatisticsId());
        //Then
        assertEquals(expectedCardioRecords, actualCardioRecordsSize);
        assertEquals(expectedPowerRecords, actualPowerRecordsSize);
        assertTrue(isNewStatisticsExists);
    }

    private StatisticsEntity getTestStatisticsEntity(){
        return StatisticsEntity.builder()
                .cardioTrainingEntity(new CardioTrainingEntity())
                .powerTrainingEntity(new PowerTrainingEntity())
                .build();
    }
}