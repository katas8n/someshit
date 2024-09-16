package com.example.shop.dao;

import com.example.shop.model.Todo;
import com.example.shop.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    Connection connection;

    public TodoDAO() throws SQLException {
        this.connection = DBConnector.getConnection();
    }

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<Todo>();

        String query = "SELECT * FROM todos";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(); // returns results
//        ResultSet resultSet = statement.executeUpdate(); // amount of changes

        while (resultSet.next()) {
            todos.add(
                    new Todo(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("hasCompleted")
                            )
            );
        }
        return todos;
    }

    public void addTodo(Todo todo) {
        try {
            String query = "INSERT INTO todos (description, hasCompleted) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, todo.getDescription());
            statement.setBoolean(2, todo.hasCompleted());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
