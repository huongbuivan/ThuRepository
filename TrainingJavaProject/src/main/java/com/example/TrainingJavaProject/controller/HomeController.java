package com.example.TrainingJavaProject.controller;

import com.example.TrainingJavaProject.dto.responses.ClientsResponse;
import com.example.TrainingJavaProject.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ClientsService clientsService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientsResponse> getAll() {
        return clientsService.getAllClients();
    }

}
