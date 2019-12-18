package com.learning.gym.star.gym;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Gym {

    private String gymId;
    private String gymName;
    private String street;
    @JsonSerialize
    private String city;
    private String buildingNumber;
}
