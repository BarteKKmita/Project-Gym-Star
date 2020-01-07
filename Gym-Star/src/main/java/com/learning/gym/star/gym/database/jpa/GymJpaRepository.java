package com.learning.gym.star.gym.database.jpa;

import com.learning.gym.star.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymJpaRepository extends JpaRepository <Gym, String> {
}
