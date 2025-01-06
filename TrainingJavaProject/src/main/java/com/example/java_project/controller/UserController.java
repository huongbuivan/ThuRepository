package com.example.java_project.controller;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDto userDto) {
        usersService.createUser(userDto);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestParam String userName, @Valid @RequestParam String password) {
        if (usersService.authenticateUser(userName, password)) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
