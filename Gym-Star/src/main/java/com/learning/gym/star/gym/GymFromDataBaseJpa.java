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
    private static final String ADD_QUERY = "INSERT INTO gym VALUES (?, ? , ? ,? , ? )";
    private static final String UPDATE_QUERY = "UPDATE gym SET gym_id=?, gym_name= ? , street = ? , city = ? , building_number= ?  WHERE gym_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM gym WHERE gym_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM gym";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM gym WHERE gym_id = ?";


    @Autowired
    public GymFromDataBaseJpa ( JdbcTemplate jdbcTemplate, GymQueryParameters gymQueryParameters ) {
        this.jdbcTemplate = jdbcTemplate;
        this.gymQueryParameters = gymQueryParameters;
    }

    @Override
    public void add ( Gym gym ) {
        Object[] param = gymQueryParameters.getQueryParameters(gym).toArray();
        jdbcTemplate.update(ADD_QUERY, param);
    }

    @Override
    public void update ( Gym gym, int index ) {
        Object[] param = gymQueryParameters.getQueryParameters(gym, index).toArray();
        jdbcTemplate.update(UPDATE_QUERY, param);
    }

    @Override
    public void delete ( int index ) {
        Object[] param = gymQueryParameters.getQueryParameters(index).toArray();
        jdbcTemplate.update(DELETE_QUERY, param);
    }

    @Override
    public List <String> getGymData () {
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        return jdbcTemplate.query(SELECT_ALL_QUERY, ( resultSet, i ) -> getDataFromQuery(resultSet));
    }

    @Override
    public String[] getGymDataById ( int id ) {
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        String[] output = jdbcTemplate.queryForObject(SELECT_ONE_QUERY, new Object[]{id}, ( resultSet, i ) -> {
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