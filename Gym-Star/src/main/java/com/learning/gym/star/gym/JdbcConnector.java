package com.learning.gym.star.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcConnector {

    JdbcPropertiesReader jdbcPropertiesReader;
    private final String url;
    private final String user;
    private final String password;

    public JdbcConnector ( JdbcPropertiesReader jdbcPropertiesReader ) {
        this.jdbcPropertiesReader = jdbcPropertiesReader;
        String[] databaseProperties = jdbcPropertiesReader.getDatabaseProperties();
        url = databaseProperties[0];
        user = databaseProperties[1];
        password = databaseProperties[2];
    }

    public JdbcConnector () {
        this(new JdbcPropertiesReader());
    }

    PreparedStatement prepareStatement ( String sqlQuery, List <String> queryParameters ) throws SQLException {
        PreparedStatement statement = prepareQuery(sqlQuery);
        for (int i = 0; i < queryParameters.size(); i++) {
            statement.setString(i + 1, queryParameters.get(i));
        }
        return statement;
    }

    private PreparedStatement prepareQuery ( String sqlQuery ) throws SQLException {
        return getConnection().prepareStatement(sqlQuery);
    }

    private Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
