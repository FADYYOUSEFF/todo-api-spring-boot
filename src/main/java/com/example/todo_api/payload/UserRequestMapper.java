package com.example.todo_api.payload;

import com.example.todo_api.Entitiy.UserApp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    UserApp map (UserRequest userRequest);
}
