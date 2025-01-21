package com.example.java_project.controller;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.service.UserTeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_team")
public class UserTeamController {
    private final UserTeamService userTeamService;

    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @PostMapping("/add-user-to-group")
    public String addUserToGroup(@Valid @RequestBody UserDto userDto) {
        try {
            userTeamService.createUserAndAddToGroup(userDto);
            return "User added to group";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
