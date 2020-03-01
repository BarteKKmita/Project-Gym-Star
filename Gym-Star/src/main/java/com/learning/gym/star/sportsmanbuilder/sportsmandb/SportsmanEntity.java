package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learning.gym.star.gym.Gym;
import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@With
@Getter
@Entity
@Table(name = "sportsmen")
public class SportsmanEntity {

    @Id
    @Column(name = "sportsman_pesel")
    private String sportsmanPesel;

    @Column(name = "sportsman_name")
    @NotEmpty
    private String name;

    @Column(name = "sportsman_surname")
    @NotEmpty
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NotNull
    private GenderChoose gender;

    @JsonIgnoreProperties()
    @ManyToOne
    @JoinColumn(
            name = "trainer_pesel",
            referencedColumnName = "trainer_pesel")
    private TrainerEntity trainer;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "statistics_id",
            referencedColumnName = "statistics_id")
    private StatisticsEntity statistics;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;
}