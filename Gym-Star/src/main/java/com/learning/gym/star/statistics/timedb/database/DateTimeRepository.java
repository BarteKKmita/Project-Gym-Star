package com.learning.gym.star.statistics.timedb.database;

import com.learning.gym.star.statistics.timedb.TrainingDateStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DateTimeRepository extends JpaRepository<TrainingDateStatisticsEntity, String> {

    @Transactional
    @Query("FROM TrainingDateStatisticsDB t WHERE t.sportsmanStatsId.statisticsId = :id ")
    List<TrainingDateStatisticsEntity> getSportsmanDateAndTimeStats(@Param("id") String statisticsId);
}
