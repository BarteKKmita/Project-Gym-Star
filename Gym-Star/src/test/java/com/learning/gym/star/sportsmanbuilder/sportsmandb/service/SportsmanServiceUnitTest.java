package com.learning.gym.star.sportsmanbuilder.sportsmandb.service;

import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanDTO;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.database.SportsmanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.M;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class SportsmanServiceUnitTest {

    @InjectMocks
    private SportsmanService service;

    @Mock
    private SportsmanRepository repository;

    @Test
    public void shouldThrowWhenSportsmanExists(){
        //Given
        SportsmanDTO testSportsman = SportsmanDTO.builder()
                .sportsmanPesel("1")
                .name("Joe")
                .surname("Ordinary")
                .gender(M)
                .build();
        doReturn(Optional.of(mock(SportsmanEntity.class))).when(repository).findById(any(String.class));
        //Then
        assertThrows(EntityExistsException.class, () -> service.addSportsman(testSportsman));
    }

    @Test
    public void shouldThrowWhenSportsmanNotExists(){
        //Given
        var pesel = "123456789";
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        //Then
        assertThrows(NoSuchElementException.class, () -> service.getSportsmanStatistics(pesel));
    }
}