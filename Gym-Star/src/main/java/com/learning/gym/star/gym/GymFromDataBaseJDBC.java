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

    private JdbcConnecotr jdbcConnecotr;
    private GymQueryParameters gymQueryParameters;

    public GymFromDataBaseJDBC () {
        jdbcConnecotr = new JdbcConnecotr();
    }

    @Override
    public void add ( Gym gym ) {
        String sql = "INSERT INTO gym Values (?, ? , ? ,? , ? )";
        List <String> queryParameters = gymQueryParameters.getQueryParameters(gym);
        changeTableData(sql, queryParameters);
    }

    @Override
    public void update ( Gym gym, int gymId ) {
        String sql = "UPDATE gym SET gym_id=?, gym_name= ? , street = ? , city = ? , building_number= ?  WHERE gym_id = ?";
        List <String> queryParameters = gymQueryParameters.getQueryParameters(gym, gymId);
        changeTableData(sql, queryParameters);
    }

    @Override
    public void remove ( int gymId ) {
        String sql = "DELETE FROM gym WHERE gym_id=?";
        changeTableData(sql, gymQueryParameters.getQueryParameters(gymId));
    }

    @Override
    public List <String> getGymData () {
        String sql = "SELECT * FROM gym";
        return getGymDataFromQuery(sql);
    }

    @Override
    public String[] getGymDataById ( int gymId ) {
        String sql = "SELECT * FROM gym WHERE gym_id = ?";
        List <String> listOfGyms = getGymDataFromQuery(sql);
        return listOfGyms.get(0).split(" ");
    }

    private List <String> getGymDataFromQuery ( String sql, List <String> queryParameters ) {
        List <String> listOfGyms = new ArrayList <>();
        try {
            ResultSet resultSet = jdbcConnecotr.prepareStatement(sql, queryParameters).executeQuery();
            listOfGyms = getGymsFromDataBaseResponse(resultSet);
        } catch (SQLException e) {
            System.out.println("Data base connection failure. Check ip address, login and password");
            e.printStackTrace();
        }
        return listOfGyms;
    }

    private List <String> getGymDataFromQuery ( String sql ) {
        return getGymDataFromQuery(sql, new ArrayList <>());
    }

    private void changeTableData ( String sql, List <String> queryParameters ) {
        try {
            PreparedStatement statement = jdbcConnecotr.prepareStatement(sql, queryParameters);
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
