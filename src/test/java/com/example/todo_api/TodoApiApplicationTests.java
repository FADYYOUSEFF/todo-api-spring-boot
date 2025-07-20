package com.example.todo_api;

import com.example.todo_api.Controller.AuthController;
import com.example.todo_api.Controller.TodoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TodoApiApplicationTests {

	@Autowired
	private AuthController authController;
	@Autowired
	private TodoController todoController;
	@Test
	void checkThatControllersIdCreatedInTheContextSuccessfully() {
		assertThat(authController).isNotNull();
		assertThat(todoController).isNotNull();
	}

}
