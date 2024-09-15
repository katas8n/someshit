package com.example.shop.servlet;

import com.example.shop.dao.UserDAO;
import com.example.shop.service.UserService;
import com.example.shop.util.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public UserService userService;
    public UserDAO userDAO;

    @Override
    public void init() {
        //
        try {
            this.userDAO = new UserDAO(DBConnector.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.userService = new UserService(userDAO);
    }

//    get, post, patch, put, delete
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String em = req.getParameter("email");
        String pass = req.getParameter("password");

        try {
            if(userService.authenticate(em, pass)) {
                res.sendRedirect("account");

            }else {
                res.sendRedirect("/login?hasError=1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
