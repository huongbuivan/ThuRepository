package com.example.java_project.dto.responses;

import lombok.Getter;

@Getter
public class UserImmutability {
    private final String userName;
    private final String email;
    private final String firstName;
    private final String lastName;

    // Constructor to initialize all fields
    public UserImmutability(String userName, String email, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
