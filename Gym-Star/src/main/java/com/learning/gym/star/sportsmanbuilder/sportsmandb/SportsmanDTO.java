package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.learning.gym.star.sportsmanbuilder.gender.GenderChoose;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class SportsmanDTO {

    @PESEL(message = "Pesel must have 11 digits")
    @NotNull(message = "Please enter pesel number.")
    private CharSequence sportsmanPesel;

    @Size(min = 2, message = "Name is to short")
    @NotEmpty(message = "Sportsman has to have name")
    private String name;

    @Size(min = 2, message = "Surname is to short")
    @NotEmpty(message = "Sportsman has to have surname")
    private String surname;


    @NotNull(message = "Please specify sportsman gender")
    private GenderChoose gender;
}
