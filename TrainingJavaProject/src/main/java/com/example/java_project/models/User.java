package com.example.java_project.models;

import com.example.java_project.dto.responses.UsersResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
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
