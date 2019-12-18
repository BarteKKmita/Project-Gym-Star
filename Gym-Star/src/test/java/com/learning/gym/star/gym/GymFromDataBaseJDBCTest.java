package com.learning.gym.star.gym;

import com.learning.gym.star.gym.database.GymFromDataBaseJDBC;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * This class was created to check the correctness of application properties.
 * <p>
 * By this I want to create JdbcConnector class with proper H2 url link, in the same way like in MySQL url.
 */


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase()
class GymFromDataBaseJDBCTest {

    @Test
    void shouldAddRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        Gym gym = Gym.builder().gymId("6")
                .gymName("1")
                .buildingNumber("1")
                .street("1")
                .city("1")
                .build();
        gymFromDataBaseJDBC.add(gym);
    }

    @Test
    void shouldRemoveRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        gymFromDataBaseJDBC.delete(3);
    }

    @Test
    void shouldUpdateRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        Gym gym = Gym.builder().gymId("0")
                .gymName("Pompa")
                .buildingNumber("1")
                .street("1")
                .city("1")
                .build();
        gymFromDataBaseJDBC.update(gym, 1);
    }

    @Test
    void shouldGetGymDataFromGym_id () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        System.out.println(gymFromDataBaseJDBC.getGymData());
    }
}