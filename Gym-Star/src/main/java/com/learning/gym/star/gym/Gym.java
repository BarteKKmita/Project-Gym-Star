package com.learning.gym.star.gym;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Gym {

    private String gym_id;
    private String gym_name;
    private String street;
    private String city;
    private String building_number;
}
