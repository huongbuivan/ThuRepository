package com.example.java_project.service.impl;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.models.User;
import com.example.java_project.repository.UsersRepository;
import com.example.java_project.service.UsersService;
import lombok.AllArgsConstructor;
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
        userRequest.setPhoneNumber(user.getPhoneNumber());
        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        usersRepository.save(userRequest);
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
        User user = usersRepository.findByUserName(userName);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }


}

