package com.learning.gym.star.sportsmanbuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SportsManDirectorTest {

    @Test
    void shouldReturnConcreteSportsMan(){
        //Given
        String expectedName = "Bartek";
        String expectedSurname = "Kmita";
        SportsManDirector sportsManDirector = new SportsManDirector(mock(MaleSportsMan.class));
        when(sportsManDirector.getConcreteSportsMan()).thenReturn(new ConcreteSportsMan(expectedName, expectedSurname));
        //When
        ConcreteSportsMan concreteSportsMan = sportsManDirector.getConcreteSportsMan();
        //Then
        assertEquals(expectedName, concreteSportsMan.getName());
        assertEquals(expectedSurname, concreteSportsMan.getSurname());
    }
}