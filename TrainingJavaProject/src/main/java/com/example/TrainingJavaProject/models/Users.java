package com.example.TrainingJavaProject.models;

import com.example.TrainingJavaProject.dto.responses.UsersResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UsersResponse mapToUsersResponse() {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(this.id);
        usersResponse.setUserName(this.userName);
        usersResponse.setEmail(this.email);
        usersResponse.setFirstName(this.firstName);
        usersResponse.setLastName(this.lastName);
        return usersResponse;
    }
}
