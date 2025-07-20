package com.example.todo_api.Controller;

import com.example.todo_api.Dto.TodoDto;
import com.example.todo_api.Entitiy.ToDo;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.TodoService;
import com.example.todo_api.payload.TodoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos() {
        List<TodoDto> todosDto = todoService.getTodos();
        return ResponseEntity.ok(todosDto);
    }

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody @Valid TodoRequest todoRequest) {
        TodoDto todoDto = todoService.addTodo(todoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoDto);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name = "todoId") @Min(value = 1) long id, @RequestBody @Valid TodoRequest todoRequest) {
        TodoDto todoDto = todoService.updateTodo(id, todoRequest);
        return ResponseEntity.ok(todoDto);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable(name = "todoId") @Min(value = 1) long id) {

        TodoDto todoDto = todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoDto);
    }
}
