package com.learning.gym.star.trainer.trainerdb;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Please enter pesel number.")
    private Long pesel;

    @NotEmpty(message = "Trainer has to have name")
    private String name;

    @NotEmpty(message = "Trainer has to have surname")
    private String surname;

    @NotNull(message = "Please specify cost per hour of training")
    private Integer cost;
}