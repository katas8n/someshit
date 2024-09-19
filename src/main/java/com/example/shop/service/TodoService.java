package com.example.shop.service;

import com.example.shop.dao.TodoDAO;
import com.example.shop.model.Todo;

import java.sql.SQLException;
import java.util.List;

public class TodoService {
    private TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public List<Todo> getAllTodos(String email) throws SQLException {
        return todoDAO.getAllTodos(email);
    }
}
