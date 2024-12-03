package com.example.TrainingJavaProject.dto.responses;

import lombok.Data;

@Data
public class UsersResponse {
    private int id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
}
