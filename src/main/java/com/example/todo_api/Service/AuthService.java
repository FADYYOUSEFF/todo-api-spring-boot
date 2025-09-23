package com.example.todo_api.Service;

import com.example.todo_api.Entitiy.UserApp;
import com.example.todo_api.Exception.BadRequestException;
import com.example.todo_api.Exception.ExceptionMessage;
import com.example.todo_api.payload.UserRequest;
import com.example.todo_api.payload.UserRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManger;
    private final JWTService jwtService;
    private final UserRequestMapper userRequestMapper;

    public AuthService(AuthenticationManager authenticationManger, JWTService jwtService, UserRequestMapper userRequestMapper) {
        this.authenticationManger = authenticationManger;
        this.jwtService = jwtService;
        this.userRequestMapper = userRequestMapper;
    }

    public String verify(UserRequest userRequest) {
        UserApp user=userRequestMapper.map(userRequest);
        Authentication authentication= authenticationManger.authenticate(
                new UsernamePasswordAuthenticationToken(user.getusername(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getusername());
        throw new BadRequestException(ExceptionMessage.BAD_REQUEST);
    }
}
