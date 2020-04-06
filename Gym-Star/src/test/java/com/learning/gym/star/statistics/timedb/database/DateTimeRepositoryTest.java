package com.learning.gym.star.statistics.timedb.database;

import com.learning.gym.star.EmbeddedMySqlProvider;
import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DateTimeRepositoryTest {

    @Autowired
    private DateTimeRepository repository;

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
    }

    @Test
    public void shouldReturnListWithOneElement(){
        //Given
        var statisticsId = "1";
        int expectedSize = 1;
        //When
        List<TrainingDateStatisticsEntity> sportsmanDateAndTimeStats = repository.getSportsmanDateAndTimeStats(statisticsId);
        int actualSize = sportsmanDateAndTimeStats.size();
        //Then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnEmptyList(){
        //Given
        var notExistingStatsId = "100";
        int expectedSize = 0;
        //When
        List<TrainingDateStatisticsEntity> sportsmanDateAndTimeStats = repository.getSportsmanDateAndTimeStats(notExistingStatsId);
        int actualSize = sportsmanDateAndTimeStats.size();
        //Then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldAddStats(){
        //Given
        var statisticsId = "1";
        int expectedSize = repository.getSportsmanDateAndTimeStats(statisticsId).size() + 1;
        var testDateTimeStatsEntity = createTestDateTimeStatsEntity(statisticsId);
        //When
        repository.saveAndFlush(testDateTimeStatsEntity);
        int actualSize = repository.getSportsmanDateAndTimeStats(statisticsId).size();
        //Then
        assertEquals(expectedSize, actualSize);
    }

    private TrainingDateStatisticsEntity createTestDateTimeStatsEntity(String statisticsId){
        return TrainingDateStatisticsEntity.builder()
                .sportsmanStatsId(StatisticsEntity.builder()
                        .statisticsId(statisticsId)
                        .build())
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
    }
}