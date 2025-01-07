package com.example.java_project.models;

import com.example.java_project.annotations.Encrypted;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.security.EncryptionProcessor;
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

    @Encrypted
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // JPA lifecycle hooks
    @PrePersist
    @PreUpdate
    public void encrypt() {
        EncryptionProcessor.encryptFields(this);
    }

    @PostLoad
    public void decrypt() {
        EncryptionProcessor.decryptFields(this);
    }

    public UsersResponse mapToUsersResponse() {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(this.id);
        usersResponse.setUserName(this.userName);
        usersResponse.setEmail(this.email);
        usersResponse.setPhoneNumber(this.phoneNumber);
        usersResponse.setFirstName(this.firstName);
        usersResponse.setLastName(this.lastName);
        return usersResponse;
    }
}
