package com.example.todo_api.Dto;

public class TodoDto {
    private long id;
    private String title;
    private String Description;
    private boolean isCompleted;

    public TodoDto(){}

    public TodoDto(long id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        Description = description;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
