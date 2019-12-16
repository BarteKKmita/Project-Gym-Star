package com.learning.gym.star.gym;

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
    private String city;
    private String buildingNumber;
}
