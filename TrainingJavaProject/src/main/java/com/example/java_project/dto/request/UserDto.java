package com.example.java_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Missing userName")
    private String userName;
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$",
            message = "Invalid phone number")
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
}
