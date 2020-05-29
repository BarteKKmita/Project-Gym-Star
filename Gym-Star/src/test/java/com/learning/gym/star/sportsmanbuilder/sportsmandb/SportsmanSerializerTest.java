package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import com.learning.gym.star.statistics.statisticsdb.StatisticsEntity;
import com.learning.gym.star.training.cardio.CardioTrainingEntity;
import com.learning.gym.star.training.power.PowerTrainingEntity;
import org.junit.jupiter.api.Test;

import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.F;
import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.M;
import static org.junit.jupiter.api.Assertions.*;

class SportsmanSerializerTest {

    private SportsmanSerializer serializer = new SportsmanSerializer();

    @Test
    public void shouldReturnSportsmanEntity(){
        //Given
        var sportsmanDTO = getSportsmanDTO();
        //When
        var sportsmanEntity = serializer.buildSportsmanFromSportsmanDTO(sportsmanDTO);
        //Then
        assertNotNull(sportsmanEntity);
        assertEquals(sportsmanDTO.getName(), sportsmanEntity.getName());
        assertEquals(sportsmanDTO.getSurname(), sportsmanEntity.getSurname());
        assertEquals(sportsmanDTO.getGender(), sportsmanEntity.getGender());
        assertEquals(sportsmanDTO.getSportsmanPesel(), sportsmanEntity.getSportsmanPesel());
    }

    @Test
    public void shouldThrowWhenSerializingNullSportsmanDTO(){
        //Given
        SportsmanDTO sportsmanDTO = null;
        //Then
        assertThrows(NullPointerException.class, () -> serializer.buildSportsmanFromSportsmanDTO(sportsmanDTO));
    }

    @Test
    public void shouldReturnSportsmanDTO(){
        //Given
        var sportsmanEntity = getSportsmanEntity();
        //When
        SportsmanDTO sportsmanDTO = serializer.getSportsmanDTOFromSportsman(sportsmanEntity);
        //Then
        assertNotNull(sportsmanDTO);
    }

    @Test
    public void shouldThrowWhenSportsmanEntityIsNull(){
        //Given
        SportsmanEntity sportsmanEntity = null;
        //Then
        assertThrows(NullPointerException.class, () -> serializer.getSportsmanDTOFromSportsman(sportsmanEntity));
    }

    private SportsmanDTO getSportsmanDTO(){
        return SportsmanDTO.builder()
                .sportsmanPesel("95031868345")
                .name("Joe")
                .surname("Ordinary")
                .gender(M)
                .build();
    }

    private SportsmanEntity getSportsmanEntity(){
        return SportsmanEntity.builder()
                .sportsmanPesel("49070428596")
                .name("Jane")
                .surname("Average")
                .gender(F)
                .statistics(StatisticsEntity.builder()
                        .cardioTrainingEntity(new CardioTrainingEntity())
                        .powerTrainingEntity(new PowerTrainingEntity())
                        .build())
                .build();
    }
}