package com.learning.gym.star.statistics.statisticsdb.controller;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.statisticsdb.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsControllerUnitTest {
    @InjectMocks
    private StatisticsController controller;

    @Mock
    private StatisticsService service;

    @Test
    void shouldReturnStatusOk(){
        //Given
        when(service.readAllStatistics()).thenReturn(prepareStatisticsList());
        //When
        ResponseEntity response = controller.getAllStatistics();
        //Them
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void shouldReturnStatusOkWhenReadingStatistics(){
        //Given
        int statisticsId = 1;
        when(service.readStatisticsById(any(String.class))).thenReturn(mock(StatisticsEntity.class));
        //When
        ResponseEntity response = controller.getStatisticsById(statisticsId);
        //Them
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void shouldReturnStatusCreated(){
        //Given
        var statisticsId = "1";
        when(service.createNewStatistics()).thenReturn(statisticsId);
        //When
        ResponseEntity response = controller.createNewStatistics();
        //Them
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
    }

    @Test
    void shouldReturnStatsId(){
        //Given
        var statisticsId = "1";
        when(service.createNewStatistics()).thenReturn(statisticsId);
        //When
        ResponseEntity response = controller.createNewStatistics();
        //Them
        assertEquals(statisticsId, response.getBody());
    }

    private List<StatisticsEntity> prepareStatisticsList(){
        List<StatisticsEntity> statistics = new ArrayList<>();
        statistics.add(mock(StatisticsEntity.class));
        return statistics;
    }
}