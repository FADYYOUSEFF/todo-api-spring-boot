package com.example.todo_api.Dto;

import com.example.todo_api.Entitiy.UserApp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(UserApp userApp);
    UserApp unMap(UserDto userDto);
}
