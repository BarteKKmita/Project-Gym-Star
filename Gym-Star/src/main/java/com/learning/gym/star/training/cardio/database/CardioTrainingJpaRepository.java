package com.learning.gym.star.training.cardio.database;

import com.learning.gym.star.training.cardio.CardioTrainingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CardioTrainingJpaRepository extends JpaRepository<CardioTrainingDB, String> {

    @Transactional
    @Modifying
    @Query("UPDATE CardioTrainingDB c SET c.trainingCount=c.trainingCount+1 WHERE c.cardioId = :id")
    void updateTrainingCounter(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE CardioTrainingDB c SET c.trainingCount=0 WHERE c.cardioId = :id")
    void resetCardioStatistics(@Param("id") String id);
}