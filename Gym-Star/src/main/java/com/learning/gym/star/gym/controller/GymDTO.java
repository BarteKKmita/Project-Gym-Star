package com.learning.gym.star.gym.controller;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class GymDTO {

    private String gymId;
    @NotEmpty
    private String gymName;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    @Valid
    private String buildingNumber;

    public String[] toStringArray(){
        return new String[]{gymId, gymName, street, city, buildingNumber};
    }
}

