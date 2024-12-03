package com.example.TrainingJavaProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class User {
    @NotEmpty(message = "Missing name")
    private String name;
    @Min(0)
    private int age;
    @Email(message = "Invalid email")
    private String email;
}
