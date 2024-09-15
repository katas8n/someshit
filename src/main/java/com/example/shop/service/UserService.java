package com.example.shop.service;

import com.example.shop.dao.UserDAO;
import com.example.shop.model.User;

import java.sql.SQLException;

public class UserService {
    public UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticate (String email, String password) throws SQLException {
        User user = userDAO.getUser(email);

        if(user != null) return  true;

        return  false;
    }
}
