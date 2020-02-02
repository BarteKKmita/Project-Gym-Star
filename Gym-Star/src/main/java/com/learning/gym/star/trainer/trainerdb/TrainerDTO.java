package com.learning.gym.star.trainer.trainerdb;


import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*
Validation will be added in the future.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TrainerDTO implements Serializable {

    @PESEL(message = "Pesel must have 11 digits")
    @NotNull(message = "Please enter pesel number.")
    private CharSequence pesel;

    @Size(min = 2, message = "Name is to short")
    @NotEmpty(message = "Trainer has to have name")
    private String name;

    @Size(min = 2, message = "Surname is to short")
    @NotEmpty(message = "Trainer has to have surname")
    private String surname;

    @NotNull(message = "Please specify cost per hour of training")
    private Integer cost;
}