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
//
//    @OneToMany
//    private SportsMan sportsMan;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trainersatgym",
            joinColumns = @JoinColumn(referencedColumnName = "trainer_pesel"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "gym_id"))
    private List<Gym> gymList;
}
