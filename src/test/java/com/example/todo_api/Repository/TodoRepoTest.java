package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.ToDo;
import com.example.todo_api.Entitiy.UserApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TodoRepoTest {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserAppRpo userRepo;

    @BeforeEach
    void setUp() {
        todoRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void checkIfCanFindTodoByUserId() {
        // arrange
        UserApp user = new UserApp("fady", "123");
        user = userRepo.save(user);

        ToDo todo1 = new ToDo("test inserting first todo", "insert todo in the h2 database", false, user);
        ToDo todo2 = new ToDo("test inserting second todo", "insert todo in the h2 database", false, user);

        todoRepo.saveAll(List.of(todo1,todo2));

        // act
        List<ToDo> result = todoRepo.findTodoByUserId(user.getId());

        // assert
        assertThat(result).hasSize(2);
        assertThat(result).extracting(ToDo::getTitle)
                .containsExactlyInAnyOrder("test inserting first todo", "test inserting second todo");
    }
}
