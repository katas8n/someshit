package com.example.shop.util;

import com.example.shop.servlet.LoginServlet;
import com.example.shop.servlet.TodoListServlet;
import com.example.shop.servlet.UserDashboardServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyRunner {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8013);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");

        servletContextHandler.setResourceBase("src/main/resources/assets");
        servletContextHandler.setWelcomeFiles(new String[]{"index.html"});

        servletContextHandler.addServlet(LoginServlet.class, "/login");
        servletContextHandler.addServlet(TodoListServlet.class, "/todo-list");
        servletContextHandler.addServlet(UserDashboardServlet.class, "/user-dashboard");

        server.setHandler(servletContextHandler);

        server.start();
        server.join();
    }
}
