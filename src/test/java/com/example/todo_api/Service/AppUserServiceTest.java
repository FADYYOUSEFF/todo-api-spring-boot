package com.example.todo_api.Service;

import com.example.todo_api.Config.PasswordConfig;
import com.example.todo_api.response.UserResponse;
import com.example.todo_api.response.UserMapper;
import com.example.todo_api.Entitiy.UserApp;
import com.example.todo_api.Exception.ExceptionMessage;
import com.example.todo_api.Exception.RecordDublicatedException;
import com.example.todo_api.Exception.UserNotFoundException;
import com.example.todo_api.Repository.UserAppRpo;
import com.example.todo_api.payload.UserRequest;
import com.example.todo_api.payload.UserRequestMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    UserAppRpo userAppRpo;

    @Mock
    UserMapper userMapper;

    @Mock
    UserRequestMapper userRequestMapper;

    @Mock
    PasswordConfig passwordConfig;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AppUserService appUserService;





    @Test
    void AppUserService_saveUser_saveUserWhenNoDuplicationOfUserName() {
        UserRequest userRequest=new UserRequest("username","password");

        UserApp user=new UserApp("username","password");

        when(userRequestMapper.map(userRequest)).thenReturn(user);
        when(userAppRpo.findByUsername(Mockito.any(String.class))).thenReturn(Optional.empty());
        when(passwordConfig.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("password-encoded");
        when(userAppRpo.save(user)).thenReturn(user);
        when(userMapper.map(user)).thenReturn(new UserResponse(user.getId(),user.getusername()));

        UserResponse userResponse =appUserService.saveUser(userRequest);

        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.getUsername()).isEqualTo(userRequest.getUsername());
    }

    @Test
    void AppUserService_saveUser_ThrowExceptionWhenDuplicateUserName(){
        UserRequest userRequest=new UserRequest("username","password");
        UserApp user=new UserApp("username","password");
        when(userRequestMapper.map(userRequest)).thenReturn(user);
        when(userAppRpo.findByUsername(user.getusername())).thenReturn(Optional.of(user));

        Assertions.assertThatThrownBy(()->{
            appUserService.saveUser(userRequest);})
                .isInstanceOf(RecordDublicatedException.class)
                .hasMessageContaining(ExceptionMessage.USERNAME_TAKEN);
    }



    @Test
    void UserAppService_findByUsername_getUserWhenItExist() {

        UserApp user=new UserApp("name","password");

        when(userAppRpo.findByUsername(user.getusername())).thenReturn(Optional.of(user));

        UserApp loadedUser=appUserService.findByUsername(user.getusername());

        Assertions.assertThat(loadedUser).isNotNull();
        Assertions.assertThat(loadedUser).extracting(UserApp::getusername).isEqualTo(user.getusername());
    }

    @Test
    void UserAppService_findByUsername_ThrowExceptionWhenUserNotExist() {
        when(userAppRpo.findByUsername(Mockito.any(String.class))).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()->{
            appUserService.findByUsername("username");
        }).isInstanceOf(UserNotFoundException.class).hasMessageContaining(ExceptionMessage.USER_NOT_FOUND);
    }

    @Test
    void UserAppService_loadUserByUsername_getUserWhenItExist() {
        UserApp user =new UserApp("name","password");

        when(userAppRpo.findByUsername(user.getusername())).thenReturn(Optional.of(user));

        UserDetails loadedUser = appUserService.loadUserByUsername(user.getusername());

        Assertions.assertThat(loadedUser).isNotNull();
        Assertions.assertThat(loadedUser.getUsername()).isEqualTo(user.getusername());

    }

    @Test
    void UserAppService_loadUserByUsername_ThrowExceptionWhenUserNotExist() {

        when(userAppRpo.findByUsername(Mockito.any(String.class))).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()->{
            appUserService.loadUserByUsername("username");
        }).isInstanceOf(UserNotFoundException.class).hasMessageContaining(ExceptionMessage.USER_NOT_FOUND);

    }


}