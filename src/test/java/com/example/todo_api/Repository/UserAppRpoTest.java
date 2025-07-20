package com.example.todo_api.Repository;

import com.example.todo_api.Entitiy.UserApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserAppRpoTest {

    @Autowired
    UserAppRpo userAppRpo;

    @BeforeEach
    void setUp() {
        userAppRpo.deleteAll();
    }

    @Test
    void CheckIfCanFindByUsername() {
        // arrange
            UserApp userApp= new UserApp("fady","123");
            userAppRpo.save(userApp);
        // act
            UserApp result=userAppRpo.findByUsername(userApp.getusername()).get();
        // assert
        assertThat(result).isNotNull();
        assertThat(result).extracting(UserApp::getusername).isEqualTo("fady");
    }
}