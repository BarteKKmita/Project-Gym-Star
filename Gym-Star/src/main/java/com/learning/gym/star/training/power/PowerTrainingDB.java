package com.learning.gym.star.training.power;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonBackReference
    private StatisticsDB statisticsDB;
}
