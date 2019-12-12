package com.learning.gym.star.gym;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GymFromDataBaseTest {

    @Autowired
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    @Test
    void testJdbcTemplateCreation () {
        //assertNotNull(dataSource);
        //assertNotNull(jdbcTemplate);
    }

    @Test
    void shouldRerunGymData () {
        //Given
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        GymQueryParameters gymQueryParameters = new GymQueryParameters();
        GymFromDataBaseJpa gymFromDataBase = new GymFromDataBaseJpa(jdbcTemplate, gymQueryParameters);
        //When
        String[] gymData = gymFromDataBase.getGymDataById(1);
        //Then
        assertNotNull(gymData);
    }
}