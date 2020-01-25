package com.learning.gym.star.trainer.trainerdb;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@ToString
@Getter
@Builder
public class TrainerDTO implements Serializable {

    @NotEmpty
    private long pesel;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private int cost;

}