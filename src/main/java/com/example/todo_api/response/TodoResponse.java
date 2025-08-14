package com.example.todo_api.response;

import java.util.UUID;

public class TodoDto {
    private UUID id;
    private String title;
    private String Description;
    private boolean isCompleted;

    public TodoDto(){}

    public TodoDto(UUID id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        Description = description;
        this.isCompleted = isCompleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
