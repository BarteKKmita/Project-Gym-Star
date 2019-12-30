package com.learning.gym.star.gym.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class GymFrameForController{

    @ApiModelProperty(hidden = true)
    private String gymId = null;
    @NotEmpty
    private String gymName;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String buildingNumber;

    @Transient
    @ApiModelProperty(hidden = true)
    private String auxiliary;
}

