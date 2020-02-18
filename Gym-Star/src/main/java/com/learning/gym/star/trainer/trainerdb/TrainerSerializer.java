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
        String trainerJson = serializeTrainerEntity(trainer);
        return deserializeTrainerEntity(trainer, trainerJson);
    }

    public TrainerEntity getTrainerFromTrainerDTO(TrainerDTO trainer){
        String trainerJson = serializeTrainerDTO(trainer);
        return deserializeTrainerDTO(trainer, trainerJson);
    }

    private TrainerDTO deserializeTrainerEntity(TrainerEntity trainer, String trainerJson){
        TrainerDTO trainerDTO = null;
        try {
            trainerDTO = objectMapper.readValue(trainerJson, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of Trainer failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
        }
        return trainerDTO;
    }

    private TrainerEntity deserializeTrainerDTO(TrainerDTO trainer, String trainerJson){
        TrainerEntity trainerEntity = null;
        try {
            trainerEntity = objectMapper.readValue(trainerJson, TrainerEntity.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of TrainerDTO failure.", e);
            LOGGER.info("TrainerDTO data. {}", trainer);
        }
        return trainerEntity;
    }

    private String serializeTrainerEntity(TrainerEntity trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of Trainer failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
        }
        return trainerJson;
    }

    private String serializeTrainerDTO(TrainerDTO trainer){
        String trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of TrainerDTO failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
        }
        return trainerJson;
    }
}