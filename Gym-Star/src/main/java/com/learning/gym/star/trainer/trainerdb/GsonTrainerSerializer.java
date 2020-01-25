package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
This class will use Gson as soon as I will handle with dependency conflict.
 */
public class GsonTrainerSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public String serializeTrainer(TrainerDB trainer){

        try {
            objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
