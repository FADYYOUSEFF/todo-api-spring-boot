package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepo extends JpaRepository<ToDo,UUID> {
    @Query("SELECT t FROM ToDo t LEFT JOIN t.user u where u.id=:id")
    List<ToDo> findTodoByUserId(UUID id);
}
