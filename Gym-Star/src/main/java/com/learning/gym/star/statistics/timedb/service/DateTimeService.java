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
import java.util.NoSuchElementException;

@Service("DateAndTimeService")
public
class DateTimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeService.class);
    private final DateTimeRepository repository;

    public DateTimeService(DateTimeRepository repository){
        this.repository = repository;
    }

    public void addTrainingDateAndTime(String statisticsId){
        LOGGER.info("Attempting to save sportsman's training date and time by his statistics id: {}", statisticsId);
        var dateTimeStats = TrainingDateStatisticsEntity.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .sportsmanStatsId(StatisticsEntity.builder().statisticsId(statisticsId).build())
                .build();
        repository.saveAndFlush(dateTimeStats);
    }

    public List<TrainingDateStatisticsEntity> getSportsManDateAndTimeStatistics(String statisticsId){
        LOGGER.info("Attempting to get sportsman's training date and time by his statistics id: {}", statisticsId);
        if (!repository.existsById(statisticsId)) {
            LOGGER.info("There is no training date and time statistics for this sportsman statistics id: {}. Status 404 returned.", statisticsId);
            throw new NoSuchElementException("There is no training date and time statistics for this sportsman statistics id: " + statisticsId);
        }
        return repository.getSportsmanDateAndTimeStats(statisticsId);
    }
}
