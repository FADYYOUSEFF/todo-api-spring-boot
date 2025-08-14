package com.example.todo_api.Dto;

import com.example.todo_api.Entitiy.ToDo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    List<TodoDto> map(List<ToDo> toDos);
    TodoDto map(ToDo toDo);
    ToDo unMap(TodoDto todoDto);
}
