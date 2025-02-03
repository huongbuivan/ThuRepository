package com.example.java_project.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.models.Team;
import com.example.java_project.models.User;
import com.example.java_project.models.UserTeam;
import com.example.java_project.repository.TeamsRepository;
import com.example.java_project.repository.UserTeamRepository;
import com.example.java_project.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
class UserTeamServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private TeamsRepository teamsRepository;

    @Mock
    private UserTeamRepository userTeamRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserTeamServiceImpl userTeamService;

    private UserDto userDto;
    private User user;
    private Team team;

    @BeforeEach
    void setUp() {
        // Mock UserDto request
        userDto = new UserDto();
        userDto.setUserName("Thu");
        userDto.setPassword("plaintextPassword"); // Will be encoded
        userDto.setEmail("thu@example.com");
        userDto.setPhoneNumber("1234567890");
        userDto.setFirstName("Thu");
        userDto.setLastName("T");
        userDto.setTeamId(1);

        // Mock User (after save)
        user = new User();
        user.setId(100);
        user.setUserName(userDto.getUserName());
        user.setPassword("hashedPassword");

        // Mock Team
        team = new Team();
        team.setId(userDto.getTeamId());
        team.setTeamName("Develop");
        team.setDescription("Initial description");
    }

    @Test
    @Transactional
    void testCreateUserAndAddToGroup_Success() {
        // Arrange
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("hashedPassword");
        when(usersRepository.save(any(User.class))).thenReturn(user);
        when(teamsRepository.findById(userDto.getTeamId())).thenReturn(Optional.of(team));
        when(userTeamRepository.save(any(UserTeam.class))).thenReturn(new UserTeam());

        // Act
        userTeamService.createUserAndAddToGroup(userDto);

        // Assert
        assertEquals("Updated with user: Thu", team.getDescription()); // Ensure description update
        verify(passwordEncoder, times(1)).encode(userDto.getPassword());  // Ensure password is encoded
        verify(usersRepository, times(1)).save(any(User.class));  // Ensure user is saved
        verify(teamsRepository, times(1)).findById(userDto.getTeamId()); // Ensure team is fetched
        verify(userTeamRepository, times(1)).save(any(UserTeam.class)); // Ensure user-team mapping is saved
    }

    @Test
    void testCreateUserAndAddToGroup_TeamNotFound() {
        // Arrange
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("hashedPassword");
        when(usersRepository.save(any(User.class))).thenReturn(user);
        when(teamsRepository.findById(userDto.getTeamId())).thenReturn(Optional.empty()); // Mock missing team

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userTeamService.createUserAndAddToGroup(userDto);
        });

        assertEquals("Group not found", exception.getMessage()); // Verify exception message

        verify(usersRepository, times(1)).save(any(User.class));  // Ensure user is saved before failure
        verify(teamsRepository, times(1)).findById(userDto.getTeamId()); // Ensure team lookup
        verify(userTeamRepository, never()).save(any(UserTeam.class)); // Ensure no user-team mapping is saved
        // Optional: Ensure rollback if using real DB, we cannot verify rollback behavior directly with mocked repositories.
    }
}
