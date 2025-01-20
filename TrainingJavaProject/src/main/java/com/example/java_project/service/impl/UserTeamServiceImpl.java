package com.example.java_project.service.impl;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.models.Team;
import com.example.java_project.models.User;
import com.example.java_project.models.UserTeam;
import com.example.java_project.repository.TeamsRepository;
import com.example.java_project.repository.UserTeamRepository;
import com.example.java_project.repository.UsersRepository;
import com.example.java_project.service.UserTeamService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserTeamServiceImpl implements UserTeamService {
    private final UsersRepository usersRepository;

    private final TeamsRepository teamsRepository;

    private final UserTeamRepository userTeamRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public User mapToUser(UserDto userDto) {
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        User userRequest = new User();
        userRequest.setUserName(userDto.getUserName());
        userRequest.setPassword(hashedPassword);
        userRequest.setEmail(userDto.getEmail());
        userRequest.setPhoneNumber(userDto.getPhoneNumber());
        userRequest.setFirstName(userDto.getFirstName());
        userRequest.setLastName(userDto.getLastName());
        return userRequest;
    }

    @Override
    @Transactional
    public void createUserAndAddToGroup(UserDto user) {
        // Create user
        User userRequest = mapToUser(user);
        User savedUser = usersRepository.save(userRequest);

        Team team = teamsRepository.findById(user.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
        team.setDescription("Updated with user: " + user.getUserName());

        // Add user to group
        UserTeam userTeam = new UserTeam();
        userTeam.setUser(savedUser);
        userTeam.setTeam(team);
        userTeamRepository.save(userTeam);
    }
}
