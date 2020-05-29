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
@NamedQuery(name = "setPersonalTrainer",
        query = "FROM TrainerEntity t WHERE t.pesel=:pesel")
public class TrainerEntity implements Serializable {

    @Id
    @Column(name = "trainer_pesel")
    @NonNull
    private String pesel;

    @NotEmpty
    @Column(name = "trainer_name")
    @NonNull
    private String name;

    @NotEmpty
    @Column(name = "trainer_surname")
    @NonNull
    private String surname;

    @NotNull
    @Column(name = "cost")
    @NonNull
    private Integer cost;

    @Getter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "trainersatgym",
            joinColumns = @JoinColumn(name = "trainer_pesel"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    private List<Gym> gym;
}
