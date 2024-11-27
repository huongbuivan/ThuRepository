package com.example.TrainingJavaProject.service;

import com.example.TrainingJavaProject.dto.responses.ClientsResponse;
import com.example.TrainingJavaProject.models.Clients;

import java.util.List;

public interface ClientsService {
    List<ClientsResponse> getAllClients();
}
