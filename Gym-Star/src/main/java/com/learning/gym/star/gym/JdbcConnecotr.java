package com.learning.gym.star.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcConnecotr {

    private final String url;
    private final String user;
    private final String password;

    public JdbcConnecotr ( String url, String user, String password ) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public JdbcConnecotr () {
        url = "jdbc:mysql://localhost:3306/firstdatabase?serverTimezone=UTC";
        user = "root";
        password = "root";
    }

    PreparedStatement prepareStatement ( String sql, List <String> queryParameters ) throws SQLException {
        PreparedStatement statement = prepareQuery(sql);
        for (int i = 0; i < queryParameters.size(); i++) {
            statement.setString(i + 1, queryParameters.get(i));
        }
        return statement;
    }

    private PreparedStatement prepareQuery ( String sql ) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    private Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
