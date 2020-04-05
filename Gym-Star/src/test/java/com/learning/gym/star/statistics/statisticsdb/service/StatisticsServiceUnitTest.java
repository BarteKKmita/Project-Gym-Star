package com.learning.gym.star.statistics.statisticsdb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.statisticsdb.database.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceUnitTest {
    @InjectMocks
    private StatisticsService service;

    @Mock
    private StatisticsRepository repository;

    @Test
    void shouldReturnCreatedStatisticsId(){
        //Given
        var expectedStatisticsId = "1";
        StatisticsEntity statistics = mock(StatisticsEntity.class);
        when(repository.saveAndFlush(any(StatisticsEntity.class))).thenReturn(statistics);
        when(statistics.getStatisticsId()).thenReturn(expectedStatisticsId);
        //When
        var outputStatistics = service.createNewStatistics();
        //Then
        assertEquals(expectedStatisticsId, outputStatistics);
    }

    @Test
    void shouldReadStatisticsWithId1(){
        //Given
        String statisticsId = "1";
        Optional<StatisticsEntity> outputStatistics = Optional.of(mock(StatisticsEntity.class));
        when(repository.findById(any(String.class))).thenReturn(outputStatistics);
        //When
        StatisticsEntity statistics = service.readStatisticsById(statisticsId);
        assertNotNull(statistics);
    }

    @Test
    void shouldThrowNoSuchElementException(){
        //Given
        String statisticsId = "1";
        //When
        assertThrows(NoSuchElementException.class, () -> service.readStatisticsById(statisticsId));
    }
}