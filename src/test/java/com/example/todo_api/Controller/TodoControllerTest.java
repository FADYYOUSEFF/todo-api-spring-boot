package com.example.todo_api.Controller;

import com.example.todo_api.Dto.TodoDto;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.TodoService;
import com.example.todo_api.payload.TodoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@MockitoBean(types = {TodoService.class, AppUserService.class})
@AutoConfigureMockMvc(addFilters = false)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TodoService todoService;

    @Autowired
    AppUserService appUserService;

    @Test
    void checkGettingTheDodosRequest() throws Exception {
        List<TodoDto> todos=new ArrayList<>();
        todos.add(new TodoDto(1L,"title","description",false));
        todos.add(new TodoDto(2L,"title2","description2",false));
        todos.add(new TodoDto(3L,"title3","description3",true));

        when(todoService.getTodos()).thenReturn(todos);

        ResultActions response = mockMvc.perform(get("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todos)));
    }
    @Test
    void checkAddingTodoRequest() throws Exception{
        TodoDto todoDto=new TodoDto(1L,"title","desc",false);

        when(todoService.addTodo(any(TodoRequest.class))).thenReturn(todoDto);

        ResultActions response = mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)));

        response.andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(todoDto)));
    }
    @Test
    void checkUpdatingTodoWithIdRequest() throws Exception{
        TodoDto todoDto=new TodoDto(1L,"title","desc",false);

        when(todoService.updateTodo(any(Long.class),any(TodoRequest.class))).thenReturn(todoDto);

        ResultActions response = mockMvc.perform(put("/api/todos/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)));

        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todoDto)));
    }
    @Test
    void checkDeletingTodoWithIdRequest() throws Exception{
        TodoDto todoDto=new TodoDto(1L,"title","des",false);

        when(todoService.deleteTodo(any(Long.class))).thenReturn(todoDto);

        ResultActions response = mockMvc.perform(delete("/api/todos/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)));
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(todoDto)));
    }
}