package com.learning.gym.star.gym.database.jdbc;

import com.learning.gym.star.gym.Gym;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class GymFromDataBaseJDBC implements GymRepository {

    private final static Logger logger = LogManager.getLogger(GymFromDataBaseJDBC.class.getName());
    private JdbcConnector jdbcConnector;
    private GymQueryParameters gymQueryParameters;
    private static final String ADD_QUERY = "INSERT INTO gym VALUES (?, ? , ? ,? , ? )";
    private static final String UPDATE_QUERY = "UPDATE gym SET gym_id=?, gym_name= ? , street = ? , city = ? , building_number= ?  WHERE gym_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM gym WHERE gym_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM gym";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM gym WHERE gym_id = ?";

    GymFromDataBaseJDBC(){
        jdbcConnector = new JdbcConnector();
        gymQueryParameters = new GymQueryParameters();
    }

    GymFromDataBaseJDBC(JdbcConnector jdbcConnector){
        this.jdbcConnector = jdbcConnector;
        gymQueryParameters = new GymQueryParameters();
    }

    @Override
    public String add(Gym gym){
        List<String> queryParameters = gymQueryParameters.getGymAsList(gym);
        changeTableData(ADD_QUERY, queryParameters);
        return gym.getGymId();
    }

    @Override
    public void update(Gym gym, int gymId){
        List<String> queryParameters = gymQueryParameters.getGymAsList(gym, gymId);
        changeTableData(UPDATE_QUERY, queryParameters);
    }

    @Override
    public void delete(int gymId){
        changeTableData(DELETE_QUERY, gymQueryParameters.getGymIdAsList(gymId));
    }

    @Override
    public List<String> getGymData(){
        return getGymDataFromQuery(SELECT_ALL_QUERY);
    }

    @Override
    public String[] getGymDataById(int gymId){
        List<String> listOfGyms = getGymDataFromQuery(SELECT_ONE_QUERY, gymQueryParameters.getGymIdAsList(gymId));
        if (listOfGyms.isEmpty()) {
            return null;
        }
        return listOfGyms.get(0).split(" ");
    }

    private List<String> getGymDataFromQuery(String sqlQuery, List<String> queryParameters){
        List<String> listOfGyms = new ArrayList<>();
        try (ResultSet resultSet = jdbcConnector.prepareStatement(sqlQuery, queryParameters).executeQuery()) {
            listOfGyms = getGymsFromDataBaseResponse(resultSet);
        } catch (SQLException e) {
            logger.error("Data base connection failure. Check ip address, login and password");
            e.printStackTrace();
        }
        return listOfGyms;
    }

    private List<String> getGymDataFromQuery(String sqlQuery){
        return getGymDataFromQuery(sqlQuery, new ArrayList<>());
    }

    private void changeTableData(String sqlQuery, List<String> queryParameters){
        try (PreparedStatement statement = jdbcConnector.prepareStatement(sqlQuery, queryParameters)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Data base connection or query failure. Check configuration, login, password and query syntax");
            e.printStackTrace();
        }
    }

    //TODO
    private List<String> getGymsFromDataBaseResponse(ResultSet resultSet) throws SQLException{
        List<String> dataFromDataBase = new ArrayList<>();
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
