package com.example.java_project.service;

import com.example.java_project.dto.request.UserDto;

public interface UserTeamService {
    void createUserAndAddToGroup(UserDto user);
}
