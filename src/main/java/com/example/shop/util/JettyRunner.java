package com.example.shop.util;

import com.example.shop.servlet.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyRunner {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8013);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        servletContextHandler.setContextPath("/");

//        TODO: SERVLET BINDING

        servletContextHandler.addServlet(LoginServlet.class, "/login");
        servletContextHandler.addServlet();
        servletContextHandler.addServlet();
//
        server.setHandler(servletContextHandler);

        server.start();
        server.join();

    }
}
