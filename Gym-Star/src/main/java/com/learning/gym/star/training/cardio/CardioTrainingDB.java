package com.learning.gym.star.training.cardio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "cardiotraining")
public class CardioTrainingDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardio_id")
    private String cardioId;

    @Column(name = "training_count ")
    private int trainingCount;

    @OneToOne(mappedBy = "cardioTrainingDB")
    @JsonBackReference
    private StatisticsDB statisticsDB;
}
