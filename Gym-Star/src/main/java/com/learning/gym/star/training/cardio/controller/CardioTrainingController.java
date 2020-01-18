package com.learning.gym.star.training.cardio.controller;

import com.learning.gym.star.training.cardio.service.CardioTrainingServiceDB;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/cardio")
public class CardioTrainingController {

    CardioTrainingServiceDB service;

    public CardioTrainingController(CardioTrainingServiceDB service){
        this.service = service;
    }

    @GetMapping("{id}")
    int getCardioTrainingCount(@PathVariable("id") int cardioId){
        return service.getCardioTrainingCount(cardioId);
    }

    @PutMapping("/train/{id}")
    void doCardioTraining(@PathVariable("id") int cardioId){
        service.doCardioTraining(cardioId);
    }

    @PutMapping("reset/{id}")
    void resetCardioStatistics(@PathVariable("id") int cardioId){
        service.resetCardioStatistics(cardioId);
    }
}
