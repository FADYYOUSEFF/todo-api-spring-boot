package com.example.todo_api.Controller;

import com.example.todo_api.response.UserResponse;
import com.example.todo_api.Service.AppUserService;
import com.example.todo_api.Service.AuthService;
import com.example.todo_api.payload.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@MockitoBean(types = {AuthService.class,AppUserService.class})
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private AppUserService appUserService;




    @Test
    void login() throws Exception {
        UserRequest userRequest = new UserRequest("username", "password");

        when(authService.verify(any(UserRequest.class))).thenReturn("token");

        ResultActions response = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));

        response.andExpect(status().isOk())
                .andExpect(content().string(containsString("token")));

    }

    @Test
    void register() throws Exception{
        UserRequest userRequest=new UserRequest("username","password");
        UserResponse userResponse =new UserResponse(UUID.randomUUID(),userRequest.getUsername());

        when(appUserService.saveUser(any(UserRequest.class))).thenReturn(userResponse);

        ResultActions response= mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));

        response.andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(userResponse)));    }
}
