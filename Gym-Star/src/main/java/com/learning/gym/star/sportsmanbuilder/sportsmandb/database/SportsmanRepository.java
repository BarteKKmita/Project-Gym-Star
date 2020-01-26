package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsmanRepository extends JpaRepository<SportsmanDB, Long> {
}
