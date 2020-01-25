package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

/*
This class will use Gson as soon as I will handle with dependency conflict.
Logger will be added after pull request.
 */
@Configuration
public class GsonTrainerSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public TrainerDTO getTrainerDTOFromTrainer(TrainerDB trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        TrainerDTO trainerDTO = null;
        try {
            trainerDTO = objectMapper.readValue(trainerJson, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return trainerDTO;
    }

    public TrainerDB getTrainerDBFromTrainerDTO(TrainerDTO trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        TrainerDB trainerDB = null;
        try {
            trainerDB = objectMapper.readValue(trainerJson, TrainerDB.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return trainerDB;
    }
}
