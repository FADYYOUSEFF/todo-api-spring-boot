package com.example.todo_api.payload;

import com.example.todo_api.Entitiy.ToDo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoRequestMapper {
    ToDo map(TodoRequest todoRequest);
}
