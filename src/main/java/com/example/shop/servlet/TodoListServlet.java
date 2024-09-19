package com.example.shop.servlet;

import com.example.shop.dao.TodoDAO;
import com.example.shop.model.Todo;
import com.example.shop.service.TodoService;
import com.example.shop.util.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/todo-list")
public class TodoListServlet extends HttpServlet {
    private TodoService todoService;

    @Override
    public void init() {
        try {
            todoService = new TodoService(new TodoDAO(DBConnector.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String userEmail = req.getUserPrincipal().getName();

            List<Todo> todos = todoService.getAllTodos(userEmail);

            req.setAttribute("todos", todos);

            req.getRequestDispatcher("/templates/todo_list.ftl").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching todo list.");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}
