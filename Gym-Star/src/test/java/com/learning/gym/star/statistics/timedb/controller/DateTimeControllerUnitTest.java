package com.learning.gym.star.statistics.timedb.controller;

import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.statistics.timedb.service.DateTimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateTimeControllerUnitTest {
    @InjectMocks
    private DateTimeController controller;

    @Mock
    private DateTimeService service;

    @Test
    void shouldReturnSportsmanDateTimeStats(){
        //Given
        int statisticsId = 1;
        int sportsmanStatsId = 7;
        when(service.getSportsManDateAndTimeStatistics(any(String.class))).thenReturn(getListDateAndTimeStats(statisticsId));
        //When
        ResponseEntity sportsmanDateAndTimeStats = controller.getSportsmanDateAndTimeStats(sportsmanStatsId);
        //Then
        assertEquals(HttpStatus.OK, sportsmanDateAndTimeStats.getStatusCode());
        assertTrue(sportsmanDateAndTimeStats.hasBody());
    }

    @Test
    void shouldReturnStatusOkWhenEmptyList(){
        //Given
        int notExistingSportsmanId = 5;
        when(service.getSportsManDateAndTimeStatistics(any(String.class))).thenReturn(Collections.emptyList());
        //When
        ResponseEntity sportsmanDateAndTimeStats = controller.getSportsmanDateAndTimeStats(notExistingSportsmanId);
        //Then
        assertEquals(HttpStatus.OK, sportsmanDateAndTimeStats.getStatusCode());
    }

    private List<TrainingDateStatisticsEntity> getListDateAndTimeStats(int statisticsId){
        TrainingDateStatisticsEntity dateTimeStat = TrainingDateStatisticsEntity.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .statisticsId(String.valueOf(statisticsId))
                .build();
        List<TrainingDateStatisticsEntity> statistics = new ArrayList<>();
        statistics.add(dateTimeStat);
        return statistics;
    }
}
