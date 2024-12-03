package com.example.TrainingJavaProject.controller;

import com.example.TrainingJavaProject.dto.responses.UsersResponse;
import com.example.TrainingJavaProject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAll() {
        return usersService.getAllUsers();
    }
}
