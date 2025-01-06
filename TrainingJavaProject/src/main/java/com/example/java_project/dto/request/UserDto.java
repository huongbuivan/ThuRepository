package com.example.java_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Missing userName")
    private String userName;
    @Email(message = "Invalid email")
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
