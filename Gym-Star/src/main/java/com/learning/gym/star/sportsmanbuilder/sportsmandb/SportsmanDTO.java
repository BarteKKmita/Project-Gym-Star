package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
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
}