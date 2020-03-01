package com.learning.gym.star.sportsmanbuilder.sportsmandb;


import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "sportsmen")
public class SportsmanEntity {

    @Id
    @Column(name = "sportsman_pesel")
    private long sportsmanPesel;

    @Column(name = "sportsman_name")
    @NotEmpty
    private String name;

    @Column(name = "sportsman_surname")
    @NotEmpty
    private String surname;

    @Column(name = "gender")
    @NotEmpty
    private GenderChoose gender;

    @NotEmpty
    @ManyToOne
    @JoinColumn(
            name = "trainer_pesel",
            referencedColumnName = "trainer_pesel")
    private TrainerEntity trainer;

    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "statistics_id",
            referencedColumnName = "statistics_id")
    private StatisticsEntity statistics;
}