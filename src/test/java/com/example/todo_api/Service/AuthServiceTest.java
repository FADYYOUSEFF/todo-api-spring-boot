package com.example.todo_api.Service;

import com.example.todo_api.Entitiy.UserApp;
import com.example.todo_api.payload.UserRequest;
import com.example.todo_api.payload.UserRequestMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {


    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @Mock
    private UserRequestMapper userRequestMapper;

    @InjectMocks
    private AuthService authService;

    @Test
    void AuthService_verify_checkUserAuthenticationWhenCredentialIsValid() {

        String token="token";
        UserRequest request = new UserRequest("username", "password");
        UserApp mappedUser = new UserApp("username", "password");
        Authentication authenticationMock = mock(Authentication.class);


        when(userRequestMapper.map(request)).thenReturn(mappedUser);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);

        when(authenticationMock.isAuthenticated()).thenReturn(true);

        when(jwtService.generateToken("username")).thenReturn(token);

        String result = authService.verify(request);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(token);
    }
}