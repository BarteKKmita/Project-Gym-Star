package com.learning.gym.star.training.power;

import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "powertraining")
public class PowerTrainingDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "power_id")
    private String powerId;

    @Column(name = "training_count ")
    private int trainingCount;

    @OneToOne(mappedBy = "powerTrainingDB")
    private StatisticsDB statisticsDB;
}
