package com.example.TrainingJavaProject.dto.responses;

import com.example.TrainingJavaProject.models.Clients;
import lombok.Data;

@Data
public class ClientsResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String role;

    public void mapFromEntity(Clients clients) {
        this.id = clients.getId();
        this.firstName = clients.getFirstName();
        this.lastName = clients.getLastName();
        this.role = clients.getRole();
    }
}
