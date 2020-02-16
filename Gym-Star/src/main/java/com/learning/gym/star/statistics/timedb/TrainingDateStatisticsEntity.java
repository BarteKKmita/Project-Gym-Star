package com.learning.gym.star.statistics.timedb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "sportsmentrainingtimestatistics")
@NamedStoredProcedureQuery(
        name = "sportsmanStats",
        procedureName = "getsportsmanstats",
        resultClasses = {TrainingDateStatisticsEntity.class})
public class TrainingDateStatisticsEntity {

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
    @JoinColumn(name = "sportsmanstats_id",
            referencedColumnName = "statistics_id")
    private StatisticsEntity sportsmanStatsId;
}
