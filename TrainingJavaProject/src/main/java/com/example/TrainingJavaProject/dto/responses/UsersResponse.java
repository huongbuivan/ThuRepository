package com.example.TrainingJavaProject.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponse {
    private int id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
}
