package com.learning.gym.star.trainer.trainerdb.database;

import com.learning.gym.star.EmbeddedMySqlProvider;
import com.learning.gym.star.trainer.trainerdb.TrainerEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrainerRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TrainerRepository repository;


    @BeforeAll
    public static void setUpClass(){
        EmbeddedMySqlProvider.setUpClass();
    }

    @AfterAll
    public static void tearDownClass(){
        EmbeddedMySqlProvider.tearDownClass();
    }

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