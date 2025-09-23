package com.example.todo_api.Service;

import com.example.todo_api.Config.PasswordConfig;
import com.example.todo_api.response.UserResponse;
import com.example.todo_api.response.UserMapper;
import com.example.todo_api.Entitiy.UserApp;
import com.example.todo_api.Entitiy.UserPrinciple;
import com.example.todo_api.Exception.ExceptionMessage;
import com.example.todo_api.Exception.RecordDublicatedException;
import com.example.todo_api.Exception.UserNotFoundException;
import com.example.todo_api.Repository.UserAppRpo;
import com.example.todo_api.payload.UserRequest;
import com.example.todo_api.payload.UserRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserService implements UserDetailsService {
    private final UserAppRpo userAppRpo;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final UserRequestMapper userRequestMapper;

    public AppUserService(UserAppRpo userAppRpo, PasswordConfig passwordConfig, UserMapper userMapper, UserRequestMapper userRequestMapper) {
        this.userAppRpo = userAppRpo;
        this.passwordConfig = passwordConfig;
        this.userMapper = userMapper;
        this.userRequestMapper = userRequestMapper;
    }

    public UserResponse saveUser(UserRequest userRequest) {
        UserApp userApp = userRequestMapper.map(userRequest);
        boolean isUserNameExist=userAppRpo.findByUsername(userApp.getusername()).isPresent();
        if(isUserNameExist){
            throw new RecordDublicatedException(ExceptionMessage.USERNAME_TAKEN);
        }
        userApp.setPassword(passwordConfig.passwordEncoder().encode(userApp.getPassword()));
        UserApp userAdded=userAppRpo.save(userApp);
        return userMapper.map(userAdded);
    }

    public UserApp findByUsername(String username){
        return userAppRpo.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userAppRpo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        return new UserPrinciple(user);
    }
}
