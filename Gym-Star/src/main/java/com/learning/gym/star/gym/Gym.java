package com.learning.gym.star.gym;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Gym")
public class Gym {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gym_id")
    private String gymId;

    @Column(name = "gym_name")
    @NotEmpty
    @Length(min = 4, message = "Gym name must have at least 4 characters")
    private String gymName;

    @Column(name = "street")
    @NotEmpty
    private String street;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "building_number")
    @NotEmpty
    private String buildingNumber;

    @Transient
    private String auxiliary;
}
