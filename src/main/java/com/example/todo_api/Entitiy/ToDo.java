package com.example.todo_api.Entitiy;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name="todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String Description;
    private boolean isCompleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")// the name of the column user_id in the table of todo as forign key from user_id
    private UserApp user;

    public ToDo() {

    }

    public ToDo(UUID id, String title, String description, boolean isCompleted, UserApp user) {
        this.id = id;
        this.title = title;
        Description = description;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public ToDo(UUID id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        Description = description;
        this.isCompleted = isCompleted;
    }

    public ToDo(String title, String description, boolean isCompleted, UserApp user) {
        this.title = title;
        this.Description = description;
        this.isCompleted = isCompleted;
        this.user = user;
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

    public void setUser(UserApp user) {
        this.user=user;
    }

    public UserApp getUser() {
        return user;
    }
}
