package com.example.shop.model;

public class Todo {
    private int id;
    private String description;
    private boolean hasCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public Todo(int id, String description, boolean hasCompleted) {
        this.id = id;
        this.description = description;
        this.hasCompleted = hasCompleted;
    }
}
