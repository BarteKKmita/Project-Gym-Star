package com.learning.gym.star.training.power.database;

import com.learning.gym.star.training.power.PowerTrainingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PowerTrainingRepository extends JpaRepository<PowerTrainingDB, String> {

    @Transactional
    @Modifying
    @Query("UPDATE PowerTrainingDB p SET p.trainingCount=p.trainingCount+1 WHERE p.powerId = :id")
    void updateTrainingCounter(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE PowerTrainingDB p SET p.trainingCount=0 WHERE p.powerId = :id")
    void resetPowerStatistics(@Param("id") String id);
}
