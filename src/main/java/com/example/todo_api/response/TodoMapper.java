package com.example.todo_api.response;

import com.example.todo_api.Entitiy.ToDo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    List<TodoResponse> map(List<ToDo> toDos);
    TodoResponse map(ToDo toDo);
    ToDo unMap(TodoResponse todoResponse);
}
