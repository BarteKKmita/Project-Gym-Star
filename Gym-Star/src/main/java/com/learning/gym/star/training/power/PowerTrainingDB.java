package com.learning.gym.star.training.power;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
