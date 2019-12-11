package com.learning.gym.star.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("database access")
public class GymFromDataBaseJpa implements GymRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GymFromDataBaseJpa ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add ( Gym gym ) {

    }

    @Override
    public void update ( Gym gym, int index ) {

    }

    @Override
    public void remove ( int index ) {
    }

    @Override
    public List <String> getGymData () {
        return null;
    }

    @Override
    public String[] getGymDataById ( int id ) {
        String sql = "SELECT * FROM gym WHERE gym_id= ?";
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        String[] output = jdbcTemplate.queryForObject(sql, new Object[]{id}, ( resultSet, i ) -> {
            String gym_id = resultSet.getString("gym_id");
            String gym_name = resultSet.getString("gym_name");
            String street = resultSet.getString("street");
            String building_number = resultSet.getString("building_number");
            return new String[]{gym_id, gym_name, street, building_number};
        });
        return output;
    }
}