package com.example.todo_api.Entitiy;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String Password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)// mapped by means that the relation user is the owner of that key
    private List<ToDo> todos;

    public UserApp(){}

    public UserApp(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        Password = password;
    }

    public UserApp(String username, String password) {
        this.username = username;
        Password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", Password='" + Password + '\'' +
                ", todos=" + todos +
                '}';
    }
}
