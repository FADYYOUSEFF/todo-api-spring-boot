package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRpo extends JpaRepository<UserApp,Long> {
    public Optional<UserApp> findByUsername(String userName);
}
