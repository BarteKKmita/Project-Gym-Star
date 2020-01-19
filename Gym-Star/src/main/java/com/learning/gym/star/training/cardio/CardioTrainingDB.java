package com.learning.gym.star.training.cardio;

import lombok.*;

import javax.persistence.*;

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
    private String cardioId;

    @Column(name = "training_count ")
    private int trainingCount;
}