package com.learning.gym.star.trainer.trainerdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class TrainerSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerSerializer.class);

    public TrainerDTO getTrainerDTOFromTrainer(@NonNull TrainerEntity trainer) throws IllegalArgumentException{
        var trainerJson = serializeTrainerEntity(trainer);
        return deserializeTrainerEntity(trainer, trainerJson);
    }

    public TrainerEntity getTrainerFromTrainerDTO(@NonNull TrainerDTO trainer) throws IllegalArgumentException{
        var trainerJson = serializeTrainerDTO(trainer);
        return deserializeTrainerDTO(trainer, trainerJson);
    }

    private TrainerDTO deserializeTrainerEntity(TrainerEntity trainer, String trainerJson) throws IllegalArgumentException{
        TrainerDTO trainerDTO;
        try {
            trainerDTO = objectMapper.readValue(trainerJson, TrainerDTO.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of Trainer failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
            throw new IllegalArgumentException("Deserialization of TrainerEntity failure. Trainer id: " + trainer.getPesel(), e);
        }
        return trainerDTO;
    }

    private TrainerEntity deserializeTrainerDTO(TrainerDTO trainer, String trainerJson) throws IllegalArgumentException{
        TrainerEntity trainerEntity;
        try {
            trainerEntity = objectMapper.readValue(trainerJson, TrainerEntity.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Deserialization of TrainerDTO failure.", e);
            LOGGER.info("TrainerDTO data. {}", trainer);
            throw new IllegalArgumentException("Deserialization of TrainerDTO failure. Trainer id: " + trainer.getPesel(), e);
        }
        return trainerEntity;
    }

    private String serializeTrainerEntity(TrainerEntity trainer) throws IllegalArgumentException{
        var trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of Trainer failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
            throw new IllegalArgumentException("Serialization of TrainerEntity failure. Trainer id: " + trainer.getPesel(), e);
        }
        return trainerJson;
    }

    private String serializeTrainerDTO(TrainerDTO trainer) throws IllegalArgumentException{
        var trainerJson = "";
        try {
            trainerJson = objectMapper.writeValueAsString(trainer);
        } catch (JsonProcessingException e) {
            LOGGER.error("Serialization of TrainerDTO failure.", e);
            LOGGER.info("Trainer data. {}", trainer);
            throw new IllegalArgumentException("Serialization of TrainerDTO failure. Trainer id: " + trainer.getPesel(), e);
        }
        return trainerJson;
    }
}