package com.learning.gym.star.statistics.time;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "sportsmentrainingtimestatistics")
public class TrainingDateStatisticsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private String statisticsId;

    @Basic
    @Column(name = "date")
    private LocalDate date;

    @Basic
    @Column(name = "time")
    private LocalTime time;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "sportsmanstats_id", referencedColumnName = "statistics_id")
    private StatisticsDB sportsmanStatsId;
}
