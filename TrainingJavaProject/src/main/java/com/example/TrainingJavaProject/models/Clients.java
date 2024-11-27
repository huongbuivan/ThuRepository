package com.example.TrainingJavaProject.models;

import com.example.TrainingJavaProject.dto.responses.ClientsResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private String role;

    public ClientsResponse mapToClientsResponse() {
        ClientsResponse clientsResponse = new ClientsResponse();
        clientsResponse.mapFromEntity(this);
        return clientsResponse;
    }
}
