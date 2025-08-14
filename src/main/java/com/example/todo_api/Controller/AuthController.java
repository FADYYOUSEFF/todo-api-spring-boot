package com.example.todo_api.Controller;

import com.example.todo_api.response.UserResponse;
import com.example.todo_api.response.UserMapper;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.AuthService;
import com.example.todo_api.payload.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
    name = "Authentication",
    description = ": used to manage the process of login and sign up"
)
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @Operation(
            description = "login user end point",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserRequest userRequest){
        String token = authService.verify(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(
            description = "register a new user end point",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest){
            UserResponse userResponse =appUserService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
