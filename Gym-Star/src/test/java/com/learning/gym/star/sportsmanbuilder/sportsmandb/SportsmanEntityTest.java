package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import org.junit.jupiter.api.Test;

import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.M;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SportsmanEntityTest {

    @Test
    public void shouldThrowWhenSportsmanEntityGenderIsNull(){
        //Given
        assertThrows(NullPointerException.class, () -> SportsmanEntity.builder()
                .sportsmanPesel("49070428596")
                .name("Jane")
                .surname("Average")
                .build());
    }

    @Test
    public void shouldThrowWhenSportsmanEntityNameIsNull(){
        //Given
        assertThrows(NullPointerException.class, () -> SportsmanEntity.builder()
                .sportsmanPesel("49070428596")
                .surname("Average")
                .gender(M)
                .build());
    }

    @Test
    public void shouldThrowWhenSportsmanEntitySurnameIsNull(){
        //Given
        assertThrows(NullPointerException.class, () -> SportsmanEntity.builder()
                .sportsmanPesel("49070428596")
                .name("Joe")
                .gender(M)
                .build());
    }
}