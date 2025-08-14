package com.example.todo_api.response;

import com.example.todo_api.Entitiy.UserApp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse map(UserApp userApp);
    UserApp unMap(UserResponse userResponse);
}
