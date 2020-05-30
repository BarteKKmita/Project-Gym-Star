package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.TestRepositoryContextSpecification;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.F;
import static org.junit.jupiter.api.Assertions.*;


class SportsmanRepositoryTest extends TestRepositoryContextSpecification {

    @Autowired
    private SportsmanRepository repository;

    @Test
    public void shouldNotBeNullWhenAutowired(){
        assertNotNull(repository);
    }

    @Test
    public void shouldReturnSportsmanEntity(){
        //Given
        var sportsmanPesel = "97112848572";
        //When
        Optional<SportsmanEntity> sportsman = repository.findById(sportsmanPesel);
        //Then
        assertFalse(sportsman.isEmpty());
    }

    @Test
    public void shouldSaveSportsmanEntity(){
        //Given
        var sportsman = getSportsman();
        int expectedSportsmenAmount = repository.findAll().size() + 1;
        //When
        repository.saveAndFlush(sportsman);
        int actualSportsmenAmount = repository.findAll().size();
        //Then
        assertEquals(expectedSportsmenAmount, actualSportsmenAmount);
    }

    private SportsmanEntity getSportsman(){
        return SportsmanEntity.builder()
                .sportsmanPesel("85120129813")
                .name("Claire")
                .surname("Underwood")
                .gender(F)
                .build();
    }
}