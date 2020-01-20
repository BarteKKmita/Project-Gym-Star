package com.learning.gym.star.statistics.statisticsdb;

import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.power.PowerTrainingDB;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter
@Entity
@Table(name = "statistics")
public class StatisticsDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String statisticsId;

    @OneToOne
    @JoinColumn(name = "cardio_id")
    private CardioTrainingDB cardioTrainingDB;

    @OneToOne
    @JoinColumn(name = "power_id")
    private PowerTrainingDB powerTrainingDB;
}
