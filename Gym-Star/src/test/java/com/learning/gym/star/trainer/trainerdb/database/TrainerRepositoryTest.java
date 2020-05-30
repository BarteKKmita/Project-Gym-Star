package com.learning.gym.star.trainer.trainerdb.database;

import com.learning.gym.star.TestRepositoryContextSpecification;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrainerRepositoryTest extends TestRepositoryContextSpecification {

    @Autowired
    private TrainerRepository repository;

    @Test
    public void shouldNotBeNullWhenAutowired(){
        assertNotNull(repository);
    }

    @Test
    public void shouldReturnTrainerEntity(){
        //Given
        var trainerPesel = "57122299175";
        //When
        TrainerEntity trainerEntity = repository.findById(trainerPesel).get();
        //Then
        assertNotNull(trainerEntity);
    }

    @Test
    public void shouldAddTrainerEntity(){
        //Given
        TrainerEntity trainer = TrainerEntity.builder()
                .cost(30)
                .name("Joe")
                .surname("Ordinary")
                .pesel("68072378517")
                .build();
        var expectedNumberOfTrainers = repository.findAll().size() + 1;
        //When
        repository.saveAndFlush(trainer);
        //Then
        assertEquals(expectedNumberOfTrainers, repository.findAll().size());
    }
}