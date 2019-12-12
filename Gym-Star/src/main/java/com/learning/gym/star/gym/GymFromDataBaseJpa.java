package com.learning.gym.star.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("database access")
public class GymFromDataBaseJpa implements GymRepository {

    private final JdbcTemplate jdbcTemplate;
    private GymQueryParameters gymQueryParameters;

    @Autowired
    public GymFromDataBaseJpa ( JdbcTemplate jdbcTemplate, GymQueryParameters gymQueryParameters ) {
        this.jdbcTemplate = jdbcTemplate;
        this.gymQueryParameters = gymQueryParameters;
    }

    @Override
    public void add ( Gym gym ) {
        String sql = "INSERT INTO gym  VALUES( ? ,  ? , ? , ? , ?)";
        Object[] param = gymQueryParameters.getQueryParameters(gym).toArray();
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void update ( Gym gym, int index ) {
        String sql = "UPDATE gym SET gym_id= ? , gym_name= ? , street = ? , city = ? , building_number= ? WHERE gym_id = ?";
        Object[] param = gymQueryParameters.getQueryParameters(gym, index).toArray();
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void remove ( int index ) {
        String sql = "DELETE FROM gym WHERE gym_id = ?";
        Object[] param = gymQueryParameters.getQueryParameters(index).toArray();
        jdbcTemplate.update(sql, param);
    }

    @Override
    public List <String> getGymData () {
        String sql = "SELECT * FROM gym";
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        List <String> gymData = jdbcTemplate.query(sql, ( resultSet, i ) -> getDataFromQuery(resultSet));
        return gymData;
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

    private String getDataFromQuery ( ResultSet resultSet ) throws SQLException {
        String gym_id = resultSet.getString("gym_id");
        String gym_name = resultSet.getString("gym_name");
        String street = resultSet.getString("street");
        String city = resultSet.getString("city");
        String building_number = resultSet.getString("building_number");
        return new StringBuilder()
                .append(gym_id).append(" ")
                .append(gym_name).append(" ")
                .append(street).append(" ")
                .append(city).append(" ")
                .append(building_number)
                .toString();
    }
}