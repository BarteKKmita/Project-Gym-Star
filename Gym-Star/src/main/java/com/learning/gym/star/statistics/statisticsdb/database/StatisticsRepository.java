package com.learning.gym.star.statistics.statisticsdb.database;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, String> {
}
