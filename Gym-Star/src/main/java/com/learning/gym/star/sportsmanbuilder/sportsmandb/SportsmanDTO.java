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
    @NotNull(message = "Please enter pesel number.")
    private Long sportsmanPesel;

    @NotEmpty(message = "Sportsman has to have name")
    private String name;

    @NotEmpty(message = "Sportsman has to have surname")
    private String surname;

    @NotNull(message = "Please specify sportsman gender")
    private GenderChoose gender;
}
