package com.learning.gym.star.statistics.timedb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsDB;
import com.learning.gym.star.statistics.timedb.database.DateTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service("date and time service")
public class DateTimeService {
    private DateTimeRepository repository;

    public DateTimeService(DateTimeRepository repository){
        this.repository = repository;
    }

    public void addTrainingDateAndTime(int statisticsId){
        TrainingDateStatisticsDB dateTimeStats = TrainingDateStatisticsDB.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .sportsmanStatsId(StatisticsDB.builder().statisticsId(String.valueOf(statisticsId)).build())
                .build();
        repository.saveAndFlush(dateTimeStats);
    }

    public List<TrainingDateStatisticsDB> getSportsManDateAndTimeStatistics(int statisticsId){
        return repository.getSportsmanDateAndTimeStats(String.valueOf(statisticsId));
    }
}
