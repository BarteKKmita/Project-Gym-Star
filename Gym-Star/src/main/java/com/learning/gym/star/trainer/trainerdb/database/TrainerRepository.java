package com.learning.gym.star.trainer.trainerdb.database;

import com.learning.gym.star.trainer.trainerdb.TrainerDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<TrainerDB, Long> {
}
