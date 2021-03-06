package com.learning.gym.star.trainer.trainerdb;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TrainerDTO implements Serializable {

    @PESEL(message = "Invalid Pesel number.")
    @NotNull(message = "Please enter pesel number.")
    private String pesel;

    @Size(min = 2, message = "Name is too short")
    @NotEmpty(message = "Trainer has to have name")
    private String name;

    @Size(min = 2, message = "Surname is too short")
    @NotEmpty(message = "Trainer has to have surname")
    private String surname;

    @NotNull(message = "Please specify cost per hour of training")
    private Integer cost;
}