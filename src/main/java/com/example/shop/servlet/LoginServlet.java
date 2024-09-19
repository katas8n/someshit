package com.example.shop.servlet;

import com.example.shop.dao.UserDAO;
import com.example.shop.service.UserService;
import com.example.shop.util.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        try {
            UserDAO userDAO = new UserDAO(DBConnector.getConnection());
            this.userService = new UserService(userDAO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.getRequestDispatcher("/templates/login.ftl").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading login page.");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Boolean hasRemembered = Boolean.valueOf(req.getParameter("hasRemembered"));

            if (userService.authenticate(email, password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", email);

                if (hasRemembered != null && hasRemembered) {
                    Cookie cookie = new Cookie("email", email);
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    res.addCookie(cookie);
                }

                res.sendRedirect("account");
            } else {
                res.sendRedirect("/login?hasError=1");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getCredentials(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.length() < 10 || password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}
