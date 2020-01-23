package com.learning.gym.star.statistics.statisticsdb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.statistics.statisticsdb.database.StatisticsRepository;
import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.power.PowerTrainingDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statistics service")
public class StatisticsService {

    private StatisticsRepository repository;

    public StatisticsService(StatisticsRepository repository){
        this.repository = repository;
    }

    public int createNewStatistics(){
        StatisticsDB stats = StatisticsDB.builder()
                .cardioTrainingDB(new CardioTrainingDB())
                .powerTrainingDB(new PowerTrainingDB())
                .build();
        return Integer.parseInt(repository.saveAndFlush(stats).getStatisticsId());
    }

    public List<StatisticsDB> readAllStatistics(){
        return repository.findAll();
    }

    public StatisticsDB readStatisticsById(int statisticsId){
        return repository.findById(String.valueOf(statisticsId)).orElseThrow();
    }
}
