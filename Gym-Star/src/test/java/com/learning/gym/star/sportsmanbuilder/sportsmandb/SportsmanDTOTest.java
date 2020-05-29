package com.learning.gym.star.sportsmanbuilder.sportsmandb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SportsmanDTOTest {

    @Test
    public void shouldThrowWhenSportsmanDTOFNameIsNull(){
        //Then
        assertThrows(NullPointerException.class, () -> SportsmanDTO.builder()
                .sportsmanPesel("95031868345")
                .surname("Ordinary")
                .build());
    }

    @Test
    public void shouldThrowWhenSportsmanDTOSurnameIsNull(){
        //Then
        assertThrows(NullPointerException.class, () -> SportsmanDTO.builder()
                .sportsmanPesel("95031868345")
                .name("Joe")
                .build());
    }

    @Test
    public void shouldThrowWhenSportsmanDTOPeselIsNull(){
        //Then
        assertThrows(NullPointerException.class, () -> SportsmanDTO.builder()
                .name("Joe")
                .surname("Ordinary")
                .build());
    }
}