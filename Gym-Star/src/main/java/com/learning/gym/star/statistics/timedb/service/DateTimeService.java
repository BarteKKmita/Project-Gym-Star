package com.learning.gym.star.statistics.timedb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import com.learning.gym.star.statistics.timedb.database.DateTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service("date and time service")
public class DateTimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeService.class);
    private final DateTimeRepository repository;

    public DateTimeService(DateTimeRepository repository){
        this.repository = repository;
    }

    public void addTrainingDateAndTime(int statisticsId){
        LOGGER.debug("Attempting to save sportsman's training date and time by his statistics id: {}", statisticsId);
        TrainingDateStatisticsEntity dateTimeStats = TrainingDateStatisticsEntity.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .sportsmanStatsId(StatisticsEntity.builder().statisticsId(String.valueOf(statisticsId)).build())
                .build();
        repository.saveAndFlush(dateTimeStats);
    }

    public List<TrainingDateStatisticsEntity> getSportsManDateAndTimeStatistics(int statisticsId){
        LOGGER.debug("Attempting to get sportsman's training date and time by his statistics id: {}", statisticsId);
        return repository.getSportsmanDateAndTimeStats(String.valueOf(statisticsId));
    }
}
