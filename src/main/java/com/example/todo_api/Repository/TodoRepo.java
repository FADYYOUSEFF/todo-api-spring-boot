package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<ToDo,Long> {
    @Query("SELECT t FROM ToDo t LEFT JOIN t.user u where u.id=:id")
    List<ToDo> findTodoByUserId(Long id);
}
