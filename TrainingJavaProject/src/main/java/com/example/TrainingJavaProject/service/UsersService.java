package com.example.TrainingJavaProject.service;

import com.example.TrainingJavaProject.dto.responses.UsersResponse;

import java.util.List;

public interface UsersService {
    List<UsersResponse> getAllUsers();
    List<UsersResponse> getUsersByName(String userName);
}