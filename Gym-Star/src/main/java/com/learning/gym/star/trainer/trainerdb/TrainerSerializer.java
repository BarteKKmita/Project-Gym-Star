package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class TrainerSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerSerializer.class);

    public TrainerDTO getTrainerDTOFromTrainer(TrainerEntity trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of Trainer failure.", e);
            LOGGER.debug("Trainer data. {}", trainer);
        }
        TrainerDTO trainerDTO = null;
        try {
            trainerDTO = objectMapper.readValue(trainerJson, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of Trainer failure.", e);
            LOGGER.debug("Trainer data. {}", trainer);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return trainerDTO;
    }

    public TrainerEntity getTrainerFromTrainerDTO(TrainerDTO trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of TrainerDTO failure.", e);
            LOGGER.debug("TrainerDTO data. {}", trainer);
        }
        TrainerEntity trainerEntity = null;
        try {
            trainerEntity = objectMapper.readValue(trainerJson, TrainerEntity.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of TrainerDTO failure.", e);
            LOGGER.debug("TrainerDTO data. {}", trainer);
        }
        return trainerEntity;
    }
}
