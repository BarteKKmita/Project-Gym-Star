package com.learning.gym.star.sportsmanbuilder.sportsmandb.database;

import com.learning.gym.star.EmbeddedMySqlProvider;
import com.learning.gym.star.sportsmanbuilder.sportsmandb.SportsmanEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.Optional;

import static com.learning.gym.star.sportsmanbuilder.gender.GenderEnum.F;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SportsmanRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SportsmanRepository repository;

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
        assertNotNull(dataSource);
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