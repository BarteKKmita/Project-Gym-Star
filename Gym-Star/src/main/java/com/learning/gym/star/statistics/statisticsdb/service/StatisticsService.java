package com.learning.gym.star.statistics.statisticsdb.service;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.statistics.statisticsdb.database.StatisticsRepository;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("StatisticsService")
public final class StatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);
    private final StatisticsRepository repository;

    public StatisticsService(StatisticsRepository repository){
        this.repository = repository;
    }

    public int createNewStatistics(){
        var stats = StatisticsEntity.builder()
                .cardioTrainingEntity(new CardioTrainingEntity())
                .powerTrainingEntity(new PowerTrainingEntity())
                .build();
        return Integer.parseInt(repository.saveAndFlush(stats).getStatisticsId());
    }

    public List<StatisticsEntity> readAllStatistics(){
        return repository.findAll();
    }

    public StatisticsEntity readStatisticsById(String statisticsId){
        LOGGER.info("Attempting to get statistics by id: {}", statisticsId);
        return repository.findById(statisticsId).orElseThrow(() -> {
            LOGGER.info("Failed to get statistics with id: {}", statisticsId);
            throw new NoSuchElementException("There is no statistics for statistics id: " + statisticsId);
        });
    }
}
