package com.learning.gym.star.trainer.trainerdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainerSerializerTest {

    private TrainerSerializer serializer = new TrainerSerializer();

    @Test
    public void shouldReturnTrainerDTO(){
        //Given
        var trainerEntity = getTrainerEntity();
        //When
        TrainerDTO trainer = serializer.getTrainerDTOFromTrainer(trainerEntity);
        //Then
        assertNotNull(trainer);
    }

    @Test
    public void shouldThrowWhenTrainerEntityIsNull(){
        //Given
        TrainerEntity trainer = null;
        //Then
        assertThrows(NullPointerException.class, () -> serializer.getTrainerDTOFromTrainer(trainer));
    }

    @Test
    public void shouldReturnTrainerEntity(){
        //Given
        var trainerDTO = getTrainerDTO();
        //When
        TrainerEntity trainer = serializer.getTrainerFromTrainerDTO(trainerDTO);
        //Then
        assertNotNull(trainer);
    }

    @Test
    public void shouldThrowWhenTrainerDTOIsNull(){
        //Given
        TrainerDTO trainer = null;
        //Then
        assertThrows(NullPointerException.class, () -> serializer.getTrainerFromTrainerDTO(trainer));
    }

    private TrainerDTO getTrainerDTO(){
        return TrainerDTO.builder()
                .name("Joe")
                .surname("Ordinary")
                .pesel("95121111629")
                .cost(30)
                .build();
    }

    private TrainerEntity getTrainerEntity(){
        return TrainerEntity.builder()
                .pesel("95121111629")
                .name("Joe")
                .surname("Ordinary")
                .cost(30)
                .build();
    }
}