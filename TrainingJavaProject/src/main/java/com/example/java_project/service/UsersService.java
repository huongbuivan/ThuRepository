package com.example.java_project.service;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.UsersResponse;


import java.util.List;

public interface UsersService {
    List<UsersResponse> getAllUsers();
    List<UsersResponse> getUsersByName(String userName);
    void createUser(UserDto user);
    boolean authenticateUser(String userName, String password);
}