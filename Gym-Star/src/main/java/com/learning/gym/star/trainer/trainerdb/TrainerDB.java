package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.gym.star.gym.Gym;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "trainer")
public class TrainerDB implements Serializable {

    @Id
    @NotNull
    @Column(name = "trainer_pesel")
    private Long pesel;

    @NotEmpty
    @Column(name = "trainer_name")
    private String name;

    @NotEmpty
    @Column(name = "trainer_surname")
    private String surname;

    @NotNull
    @Column(name = "cost")
    private Integer cost;

    @Getter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "trainersatgym",
            joinColumns = @JoinColumn(name = "trainer_pesel"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    private List<Gym> gym;
}
