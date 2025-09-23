package com.example.todo_api.Service;

import com.example.todo_api.response.TodoResponse;
import com.example.todo_api.response.TodoMapper;
import com.example.todo_api.Entitiy.ToDo;
import com.example.todo_api.Entitiy.UserApp;
import com.example.todo_api.Exception.ExceptionMessage;
import com.example.todo_api.Exception.RecordNotFoundException;
import com.example.todo_api.Repository.TodoRepo;
import com.example.todo_api.payload.TodoRequest;
import com.example.todo_api.payload.TodoRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepo todoRepo;
    private final TodoMapper todoMapper;
    private final AppUserService appUserService;
    private final TodoRequestMapper todoRequestMapper;

    public TodoService(TodoRepo todoRepo, TodoMapper todoMapper, AppUserService appUserService, TodoRequestMapper todoRequestMapper) {
        this.todoRepo = todoRepo;
        this.todoMapper = todoMapper;
        this.appUserService = appUserService;
        this.todoRequestMapper = todoRequestMapper;
    }

    public TodoResponse addTodo(TodoRequest todoRequest) {
        ToDo todo=todoRequestMapper.map(todoRequest);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = appUserService.findByUsername(username);
        ToDo newTodo = new ToDo();
        newTodo.setUser(userApp);
        newTodo.setTitle(todo.getTitle());
        newTodo.setCompleted(todo.isCompleted());
        newTodo.setDescription(todo.getDescription());
        todoRepo.save(newTodo);
        return todoMapper.map(newTodo);
    }


    public ToDo getTodoById(UUID id) {
        return todoRepo.findById(id).orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.RECORD_NOT_FOUND));
    }

    public TodoResponse updateTodo(UUID id, TodoRequest todoRequest) {
        ToDo todo=todoRequestMapper.map(todoRequest);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ToDo todoToBeUpdated = this.getTodoById(id);
        // throw record not found exception in case the todos it is not exist or the todos stored in the database not for the given user
        if (todoToBeUpdated == null || !todoToBeUpdated.getUser().getusername().equals(username)) {
            throw new RecordNotFoundException(ExceptionMessage.RECORD_NOT_FOUND);
        }
        todoToBeUpdated.setTitle(todo.getTitle());
        todoToBeUpdated.setDescription(todo.getDescription());
        todoToBeUpdated.setCompleted(todo.isCompleted());
        todoRepo.save(todoToBeUpdated);
        return todoMapper.map(todoToBeUpdated);
    }

    public TodoResponse deleteTodo(UUID id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ToDo todo = this.getTodoById(id);
        if (todo == null || !todo.getUser().getusername().equals(username)) {
            throw new RecordNotFoundException(ExceptionMessage.RECORD_NOT_FOUND);
        }
        todoRepo.deleteById(id);
        return todoMapper.map(todo);
    }

    public List<TodoResponse> getTodos() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = appUserService.findByUsername(username);
        List<ToDo> toDos = todoRepo.findTodoByUserId(userApp.getId());
        return todoMapper.map(toDos);
    }
}
