package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainerSerializer {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TrainerDTO getTrainerDTOFromTrainer(TrainerDB trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            logger.error("Serialization of Trainer failure.", e);
            logger.debug("Trainer data. {}", trainer);
        }

        TrainerDTO trainerDTO = null;
        try {
            trainerDTO = objectMapper.readValue(trainerJson, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("Deserialization of Trainer failure.", e);
            logger.debug("Trainer data. {}", trainer);
        }
        return trainerDTO;
    }

    public TrainerDB getTrainerDBFromTrainerDTO(TrainerDTO trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            logger.error("Serialization of TrainerDTO failure.", e);
            logger.debug("TrainerDTO data. {}", trainer);
        }

        TrainerDB trainerDB = null;
        try {
            trainerDB = objectMapper.readValue(trainerJson, TrainerDB.class);
        } catch (JsonProcessingException e) {
            logger.error("Deserialization of TrainerDTO failure.", e);
            logger.debug("TrainerDTO data. {}", trainer);
        }
        return trainerDB;
    }
}
