package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository("gym database access")
class GymFromDataBaseJdbcSpring implements GymRepository{

    private final JdbcTemplate jdbcTemplate;
    private GymQueryParameters gymQueryParameters;
    private static final String ADD_QUERY = "INSERT INTO gym VALUES (?, ? , ? ,? , ? )";
    private static final String UPDATE_QUERY = "UPDATE gym SET gym_id=?, gym_name= ? , street = ? , city = ? , building_number= ?  WHERE gym_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM gym WHERE gym_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM gym";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM gym WHERE gym_id = ?";


    @Autowired
    GymFromDataBaseJdbcSpring(JdbcTemplate jdbcTemplate, GymQueryParameters gymQueryParameters){
        this.jdbcTemplate = jdbcTemplate;
        this.gymQueryParameters = gymQueryParameters;
    }

    @Override
    public String add(Gym gym){
        KeyHolder generatedIdHolder = new GeneratedKeyHolder();
        List <String> queryParameters = gymQueryParameters.getQueryParameters(gym);
        jdbcTemplate.update(connection -> {
            return getPreparedStatement(queryParameters, connection);
        }, generatedIdHolder);
        return String.valueOf(generatedIdHolder.getKey());
    }

    @Override
    public void update(Gym gym, int index){
        Object[] param = gymQueryParameters.getQueryParameters(gym, index).toArray();
        jdbcTemplate.update(UPDATE_QUERY, param);
    }

    @Override
    public void delete(int index){
        Object[] param = gymQueryParameters.getQueryParameters(index).toArray();
        jdbcTemplate.update(DELETE_QUERY, param);
    }

    @Override
    public List <String> getGymData(){
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        return jdbcTemplate.query(SELECT_ALL_QUERY, (resultSet, i) -> getDataFromQuery(resultSet));
    }

    @Override
    public String[] getGymDataById(int id){
        //Lambda expression copied from tutorial code. I don't understand whole core of it yet.
        String[] output = jdbcTemplate.queryForObject(SELECT_ONE_QUERY, new Object[]{id}, (resultSet, i) -> {
            return this.getDataFromQuery(resultSet).split(" ");
        });
        return output;
    }

    private String getDataFromQuery(ResultSet resultSet) throws SQLException{
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

    private PreparedStatement getPreparedStatement(List <String> queryParameters, Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement((ADD_QUERY), Statement.RETURN_GENERATED_KEYS);
        for(int i = 0; i < queryParameters.size(); i++) {
            preparedStatement.setString(i + 1, queryParameters.get(i));
        }
        return preparedStatement;
    }
}