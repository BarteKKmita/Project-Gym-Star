package com.learning.gym.star.training;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "cardiotraining")
public class CardioTrainingDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardio_id")
    @NotNull
    private String cardio_Id;

    @Column(name = "training_count ")
    @NotEmpty
    private Integer trainingCount;
}
