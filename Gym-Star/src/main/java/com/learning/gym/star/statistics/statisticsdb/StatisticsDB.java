package com.learning.gym.star.statistics.statisticsdb;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.gym.star.training.cardio.CardioTrainingDB;
import com.learning.gym.star.training.power.PowerTrainingDB;
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
public class StatisticsDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private String statisticsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardio_id")
    @JsonManagedReference
    private CardioTrainingDB cardioTrainingDB;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "power_id")
    private PowerTrainingDB powerTrainingDB;
}
