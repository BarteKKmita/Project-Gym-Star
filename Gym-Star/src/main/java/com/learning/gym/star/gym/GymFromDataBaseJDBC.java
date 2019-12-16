package com.learning.gym.star.gym;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository("database access JDBC")
public class GymFromDataBaseJDBC implements GymRepository {

    private JdbcConnector jdbcConnecotr;
    private GymQueryParameters gymQueryParameters;
    private static final String ADD_QUERY = "INSERT INTO gym VALUES (?, ? , ? ,? , ? )";
    private static final String UPDATE_QUERY = "UPDATE gym SET gym_id=?, gym_name= ? , street = ? , city = ? , building_number= ?  WHERE gym_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM gym WHERE gym_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM gym";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM gym WHERE gym_id = ?";

    public GymFromDataBaseJDBC () {
        jdbcConnecotr = new JdbcConnector();
    }

    public GymFromDataBaseJDBC ( JdbcConnector jdbcConnecotr ) {
        this.jdbcConnecotr = jdbcConnecotr;
    }

    @Override
    public void add ( Gym gym ) {
        List <String> queryParameters = gymQueryParameters.getQueryParameters(gym);
        changeTableData(ADD_QUERY, queryParameters);
    }

    @Override
    public void update ( Gym gym, int gymId ) {
        List <String> queryParameters = gymQueryParameters.getQueryParameters(gym, gymId);
        changeTableData(UPDATE_QUERY, queryParameters);
    }

    @Override
    public void delete ( int gymId ) {
        changeTableData(DELETE_QUERY, gymQueryParameters.getQueryParameters(gymId));
    }

    @Override
    public List <String> getGymData () {
        return getGymDataFromQuery(SELECT_ALL_QUERY);
    }

    @Override
    public String[] getGymDataById ( int gymId ) {
        List <String> listOfGyms = getGymDataFromQuery(SELECT_ONE_QUERY);
        return listOfGyms.get(0).split(" ");
    }

    private List <String> getGymDataFromQuery ( String sqlQuery, List <String> queryParameters ) {
        List <String> listOfGyms = new ArrayList <>();
        try {
            ResultSet resultSet = jdbcConnecotr.prepareStatement(sqlQuery, queryParameters).executeQuery();
            listOfGyms = getGymsFromDataBaseResponse(resultSet);
        } catch (SQLException e) {
            System.out.println("Data base connection failure. Check ip address, login and password");
            e.printStackTrace();
        }
        return listOfGyms;
    }

    private List <String> getGymDataFromQuery ( String sqlQuery ) {
        return getGymDataFromQuery(sqlQuery, new ArrayList <>());
    }

    private void changeTableData ( String sqlQuery, List <String> queryParameters ) {
        try {
            PreparedStatement statement = jdbcConnecotr.prepareStatement(sqlQuery, queryParameters);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Data base connection or query failure. Check configuration, login, password and query syntax");
            e.printStackTrace();
        }
    }

    //TODO
    private List <String> getGymsFromDataBaseResponse ( ResultSet resultSet ) throws SQLException {
        List <String> dataFromDataBase = new ArrayList <>();
        while (resultSet.next()) {
            String gym_id = resultSet.getString("gym_id");
            String gym_name = resultSet.getString("gym_name");
            String street = resultSet.getString("street");
            String city = resultSet.getString("city");
            String building_number = resultSet.getString("building_number");
            StringBuilder singleGymData = new StringBuilder()
                    .append(gym_id).append(" ")
                    .append(gym_name).append(" ")
                    .append(street).append(" ")
                    .append(city).append(" ")
                    .append(building_number);
            dataFromDataBase.add(singleGymData.toString());
        }
        return dataFromDataBase;
    }
}
