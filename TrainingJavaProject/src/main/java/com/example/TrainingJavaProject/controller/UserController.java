package com.example.TrainingJavaProject.controller;

import com.example.TrainingJavaProject.dto.request.UserDto;
import com.example.TrainingJavaProject.dto.responses.UsersResponse;
import com.example.TrainingJavaProject.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAll() {
        return usersService.getAllUsers();
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getFilterByName(@RequestParam String userName) {
        return usersService.getUsersByName(userName);
    }

    @PostMapping("/")
    public String registerUser(@Valid @RequestBody UserDto userDto) {
        usersService.createUser(userDto);
        return "User registered successfully!";
    }
}
