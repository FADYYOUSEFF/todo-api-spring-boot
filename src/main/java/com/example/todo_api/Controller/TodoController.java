package com.example.todo_api.Controller;

import com.example.todo_api.response.TodoResponse;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.TodoService;
import com.example.todo_api.payload.TodoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Valid
@RestController
@RequestMapping("/api/todos")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Todo",
        description = ": used to manage the process of create, delete, update, and get todos for the each user individually"
)
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    AppUserService appUserService;

    @Operation(
           description = "Get Todos end point for user",
            responses = {
                   @ApiResponse(
                           description = "success",
                           responseCode = "200"
                   )
            }
    )
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos() {
        List<TodoResponse> todosDto = todoService.getTodos();
        return ResponseEntity.ok(todosDto);
    }
    @Operation(
            description = "Add Todo end point for user",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<TodoResponse> addTodo(@RequestBody @Valid TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.addTodo(todoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);
    }

    @Operation(
            description = "Update Todo end point for user with todo id",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable(name = "todoId") UUID id, @RequestBody @Valid TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.updateTodo(id, todoRequest);
        return ResponseEntity.ok(todoResponse);
    }

    @Operation(
            description = "Delete Todo end point for user with todo id",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable(name = "todoId") UUID id) {

        TodoResponse todoResponse = todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoResponse);
    }
}
