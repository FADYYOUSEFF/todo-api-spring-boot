package com.example.todo_api.Controller;

import com.example.todo_api.Dto.UserDto;
import com.example.todo_api.Dto.UserMapper;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.AuthService;
import com.example.todo_api.payload.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserRequest userRequest){
        String token = authService.verify(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRequest userRequest){
            UserDto userDto=appUserService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
