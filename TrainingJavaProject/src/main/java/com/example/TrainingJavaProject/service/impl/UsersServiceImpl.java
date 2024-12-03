package com.example.TrainingJavaProject.service.impl;

import com.example.TrainingJavaProject.dto.responses.UsersResponse;
import com.example.TrainingJavaProject.models.Users;
import com.example.TrainingJavaProject.repository.UsersRepository;
import com.example.TrainingJavaProject.service.CalculatorFunctionalInterface;
import com.example.TrainingJavaProject.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<UsersResponse> getAllUsers() {
        List<Users> listClients = usersRepository.findAll();
        return  listClients.stream().map(Users::mapToUsersResponse) //Method reference
                .collect(Collectors.toList());
        // map: Function<T, R>
        // .collect...: Consumer<T>
    }
}
