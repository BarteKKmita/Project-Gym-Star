package com.learning.gym.star.trainer.trainerdb;

import com.learning.gym.star.gym.Gym;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "trainers")
public class TrainerDB {

    @Id
    @NotEmpty
    @Column(name = "trainer_pesel")
    private long pesel;

    @NotEmpty
    @Column(name = "trainer_name")
    private String name;

    @NotEmpty
    @Column(name = "trainer_surname")
    private String surname;

    @NotEmpty
    @Column(name = "cost")
    private int cost;

    @ManyToMany()
    @JoinTable(name = "trainersatgym",
            joinColumns = @JoinColumn(name = "trainer_pesel"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    private List<Gym> gym;
}
