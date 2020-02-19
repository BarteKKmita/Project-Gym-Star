package com.learning.gym.star.statistics.statisticsdb;

import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.power.PowerTrainingEntity;
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
@Table(name = "statistics")
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private String statisticsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardio_id")
    private CardioTrainingEntity cardioTrainingEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "power_id")
    private PowerTrainingEntity powerTrainingEntity;
}
