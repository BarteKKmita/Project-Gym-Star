package com.learning.gym.star.trainer.trainerdb;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TrainerDTO implements Serializable {

    @NotNull(message = "Please enter pesel number.")
    private Long pesel;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotNull
    private Integer cost;

}