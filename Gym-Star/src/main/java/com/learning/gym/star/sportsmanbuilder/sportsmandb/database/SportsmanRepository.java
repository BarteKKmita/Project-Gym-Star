package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsmanRepository extends JpaRepository<SportsmanEntity, Long> {
}
