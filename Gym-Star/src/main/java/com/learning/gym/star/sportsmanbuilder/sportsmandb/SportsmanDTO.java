package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import com.learning.gym.star.statistics.statisticsdb.StatisticsDB;
import com.learning.gym.star.trainer.trainerdb.TrainerDB;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class SportsmanDTO {

    @NotNull
    private Long sportsmanPesel;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotNull
    private GenderChoose gender;

    @JsonIgnore
    private TrainerDB trainer;

    @JsonIgnore
    private StatisticsDB statistics;
}
