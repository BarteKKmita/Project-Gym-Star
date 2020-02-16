package com.learning.gym.star.statistics.statisticsdb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.statisticsdb.database.StatisticsRepository;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statistics service")
public final class StatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);
    private final StatisticsRepository repository;

    public StatisticsService(StatisticsRepository repository){
        this.repository = repository;
    }

    public int createNewStatistics(){
        var stats = StatisticsEntity.builder()
                .cardioTrainingDB(new CardioTrainingEntity())
                .powerTrainingDB(new PowerTrainingEntity())
                .build();
        return Integer.parseInt(repository.saveAndFlush(stats).getStatisticsId());
    }

    public List<StatisticsEntity> readAllStatistics(){
        return repository.findAll();
    }

    public StatisticsEntity readStatisticsById(int statisticsId){
        LOGGER.debug("Attempting to get statistics by id: {}", statisticsId);
        return repository.findById(String.valueOf(statisticsId)).orElseThrow();
    }
}
