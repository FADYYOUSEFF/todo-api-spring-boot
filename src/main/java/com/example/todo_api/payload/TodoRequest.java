package com.example.todo_api.payload;

import jakarta.validation.Valid;

@Valid
public class TodoRequest {
    private String title;
    private String Description;
    private boolean isCompleted;

    public TodoRequest(String title, String description, boolean isCompleted) {
        this.title = title;
        Description = description;
        this.isCompleted = isCompleted;
    }

    public TodoRequest() {
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
