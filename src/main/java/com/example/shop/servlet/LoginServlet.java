package com.example.shop.servlet;

import com.example.shop.dao.UserDAO;
import com.example.shop.service.UserService;
import com.example.shop.util.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

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
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Boolean hasRemembered = Boolean.valueOf(req.getParameter("hasRemembered"));

            // TODO
            if(Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getValue().equals(email))) return;

            Cookie cookie = new Cookie("email", email);

            res.addCookie(cookie);

            if(userService.authenticate(email, password)) {
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

    public boolean getCredentials(HttpServletRequest req, HttpServletResponse res) {
        String em = req.getParameter("email");
        String pass = req.getParameter("password");

        if(em.length() < 10) return false;

        return true;
    }
}
