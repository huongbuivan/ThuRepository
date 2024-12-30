package com.example.TrainingJavaProject.service.impl;

import com.example.TrainingJavaProject.dto.request.UserDto;
import com.example.TrainingJavaProject.dto.responses.UsersResponse;
import com.example.TrainingJavaProject.models.User;
import com.example.TrainingJavaProject.repository.UsersRepository;
import com.example.TrainingJavaProject.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UsersResponse> getAllUsers() {
        List<User> listClients = usersRepository.findAll();
        return listClients.stream().map(User::mapToUsersResponse) //Method reference
                .collect(Collectors.toList());
        // map: Function<T, R>
        // .collect...: Consumer<T>
    }

    @Override
    public List<UsersResponse> getUsersByName(String userName) {
        List<User> listClients = usersRepository.findByUserNameContainingIgnoreCase(userName);
        return listClients.stream().map(User::mapToUsersResponse) //Method reference
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDto user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User userRequest = new User();
        userRequest.setUserName(user.getUserName());
        userRequest.setPassword(hashedPassword);
        userRequest.setEmail(user.getEmail());
        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        usersRepository.save(userRequest);
    }
}

