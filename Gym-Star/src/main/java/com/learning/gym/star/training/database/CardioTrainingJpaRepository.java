package com.learning.gym.star.training.database;

import com.learning.gym.star.training.CardioTrainingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CardioTrainingJpaRepository extends JpaRepository<CardioTrainingDB, String> {

    @Transactional
    @Modifying
    @Query("UPDATE CardioTrainingDB c SET c.trainingCount=c.trainingCount+1 WHERE c.cardio_Id = :id")
    void updateTrainingCounter(@Param("id") String id);
}
