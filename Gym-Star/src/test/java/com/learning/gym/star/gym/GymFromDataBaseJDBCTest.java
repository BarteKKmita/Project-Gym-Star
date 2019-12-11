package com.learning.gym.star.gym;

import org.junit.jupiter.api.Test;


/**
 * This class was created to check the correctness of application properties.
 * <p>
 * By this I want to create JdbcConnector class with proper H2 url link, in the same way like in MySQL url.
 */
class GymFromDataBaseJDBCTest {

    @Test
    void shouldAddRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        Gym gym = Gym.builder().gym_id("6")
                .gym_name("1")
                .building_number("1")
                .street("1")
                .city("1")
                .build();
        gymFromDataBaseJDBC.add(gym);
    }

    @Test
    void shouldRemoveRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        gymFromDataBaseJDBC.remove(3);
    }

    @Test
    void shouldUpdateRecord () {
        GymFromDataBaseJDBC gymFromDataBaseJDBC = new GymFromDataBaseJDBC();
        Gym gym = Gym.builder().gym_id("0")
                .gym_name("Pompa")
                .building_number("1")
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