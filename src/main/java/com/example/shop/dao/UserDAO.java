package com.example.shop.dao;

import com.example.shop.model.User;
import org.springframework.security.crypto.bcrypt;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
        } else {
            return  null;
        }

    }

    public User addUser(String email, String password) throws SQLException {

        String query = "INSERT INTO users (email, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        int resultSet = statement.executeUpdate();

        if(resultSet > 0) {
            User user = this.getUser(email);
        }

        return null;
    }
}
