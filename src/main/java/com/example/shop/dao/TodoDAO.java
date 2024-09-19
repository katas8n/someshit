package com.example.shop.dao;

import com.example.shop.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private Connection connection;
    public TodoDAO(Connection connection) {
        this.connection = connection;
    }
    public List<Todo> getAllTodos(String email) throws SQLException {
        List<Todo> todos = new ArrayList<>();

        String query = "SELECT t.id, t.description, t.hasCompleted " +
                "FROM todos t " +
                "JOIN users u ON t.user_id = u.id " +
                "WHERE u.email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    todos.add(
                            new Todo(
                                    resultSet.getInt("id"),
                                    resultSet.getString("description"),
                                    resultSet.getBoolean("hasCompleted")
                            )
                    );
                }
            }
        }

        return todos;
    }

    public void addTodo(Todo todo) {
        String query = "INSERT INTO todos (description, hasCompleted) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, todo.getDescription());
            statement.setBoolean(2, todo.hasCompleted());

            // Execute the insert
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding a new Todo: " + e.getMessage());
        }
    }
}
