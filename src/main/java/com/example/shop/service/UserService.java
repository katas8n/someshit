package com.example.shop.service;

import com.example.shop.dao.UserDAO;
import com.example.shop.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.SQLException;

public class UserService {
    public UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticate (String email, String password) throws SQLException {
        User user = userDAO.getUser(email);

        if(user != null || BCrypt.checkpw(password, user.getPassword()) ) return  true;

        return  false;
    }

    public void registerUser(String email, String password) throws SQLException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(0, email, password);

        userDAO.addUser(email, password);
    }
}
