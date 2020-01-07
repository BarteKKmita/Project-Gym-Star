package com.learning.gym.star.gym.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcConnector {

    private final String url;
    private final String user;
    private final String password;

    public JdbcConnector(JdbcPropertiesReader jdbcPropertiesReader){
        url = jdbcPropertiesReader.getURL();
        user = jdbcPropertiesReader.getUsername();
        password = jdbcPropertiesReader.getPassword();
    }

    JdbcConnector(){
        this(new JdbcPropertiesReader());
    }

    PreparedStatement prepareStatement(String sqlQuery, List <String> queryParameters) throws SQLException{
        PreparedStatement statement = prepareQuery(sqlQuery);
        for (int i = 0; i < queryParameters.size(); i++) {
            statement.setString(i + 1, queryParameters.get(i));
        }
        return statement;
    }

    private PreparedStatement prepareQuery(String sqlQuery) throws SQLException{
        return getConnection().prepareStatement(sqlQuery);
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
