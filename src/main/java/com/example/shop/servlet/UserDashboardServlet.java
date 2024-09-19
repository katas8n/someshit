package com.example.shop.servlet;

import com.example.shop.model.User;
import com.example.shop.service.UserService;
import com.example.shop.dao.UserDAO;
import com.example.shop.util.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user-dashboard")
public class UserDashboardServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        try {
            userService = new UserService(new UserDAO(DBConnector.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                res.sendRedirect("/login");
                return;
            }

            String userEmail = (String) session.getAttribute("user");

            User user = userService.getUser(userEmail);

            req.setAttribute("user", user);

            req.getRequestDispatcher("/templates/user_dashboard.ftl").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching user data.");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}
