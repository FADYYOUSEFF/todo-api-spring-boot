package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAppRpo extends JpaRepository<UserApp, UUID> {
    public Optional<UserApp> findByUsername(String userName);
}
