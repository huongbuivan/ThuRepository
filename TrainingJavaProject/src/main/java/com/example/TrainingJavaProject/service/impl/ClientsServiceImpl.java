package com.example.TrainingJavaProject.service.impl;

import com.example.TrainingJavaProject.dto.responses.ClientsResponse;
import com.example.TrainingJavaProject.models.Clients;
import com.example.TrainingJavaProject.repository.ClientsRepository;
import com.example.TrainingJavaProject.service.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ClientsServiceImpl implements ClientsService {
    @Autowired
    ClientsRepository clientsRepository;

    @Override
    public List<ClientsResponse> getAllClients() {
        List<Clients> listClients = clientsRepository.findAll();
        return  listClients.stream().map(Clients::mapToClientsResponse) //Method reference
                .collect(Collectors.toList());
        // map: Function<T, R>
        // .collect...: Consumer<T>
    }
}
