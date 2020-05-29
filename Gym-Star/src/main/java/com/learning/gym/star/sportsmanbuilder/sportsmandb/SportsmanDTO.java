package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.learning.gym.star.sportsmanbuilder.gender.GenderEnum;
import com.learning.gym.star.sportsmanbuilder.gender.validation.GenderValidationCheck;
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

    @PESEL(message = "Pesel must have proper number of digits and has to be valid")
    @NotNull(message = "Please enter pesel number.")
    private String sportsmanPesel;

    @Size(min = 2, message = "Name is too short")
    @NotEmpty(message = "Sportsman has to have name")
    @NonNull
    private String name;

    @Size(min = 2, message = "Surname is too short")
    @NotEmpty(message = "Sportsman has to have surname")
    @NonNull
    private String surname;

    @GenderValidationCheck
    @NotNull(message = "Please specify sportsman gender")
    @NonNull
    private GenderEnum gender;
}
