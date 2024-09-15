package com.example.shop.dao;

import com.example.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUser(String email) throws SQLException {

        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            return  new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }else {
            return  null;
        }

    }
}
