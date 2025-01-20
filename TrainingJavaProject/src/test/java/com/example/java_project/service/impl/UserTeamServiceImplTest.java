package com.example.java_project.service.impl;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.models.Team;
import com.example.java_project.repository.TeamsRepository;
import com.example.java_project.repository.UserTeamRepository;
import com.example.java_project.repository.UsersRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_project.models.User;
import com.example.java_project.models.UserTeam;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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

    @Test
    void testMockGroupRepository() {
        int teamId = 2;
        Team team = new Team();
        team.setId(teamId);
        team.setTeamName("develop");
        team.setDescription("description");

        when(teamsRepository.findById(teamId)).thenReturn(Optional.of(team));

        Optional<Team> result = teamsRepository.findById(teamId);
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getId());
    }

    @Test
    void testCreateUserAndAddToTeam_GroupNotFound() {
        // Arrange
        int teamId = 1;

        UserDto userDto = new UserDto();
        userDto.setUserName("thu1");
        userDto.setEmail("thu1@a.c");
        userDto.setPassword("123");
        userDto.setFirstName("thu");
        userDto.setLastName("tran");
        userDto.setTeamId(teamId);

        when(teamsRepository.findById(teamId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                userTeamService.createUserAndAddToGroup(userDto)
        );

        assertEquals("Group not found", exception.getMessage());
        verify(usersRepository, never()).save(any(User.class));
        verify(userTeamRepository, never()).save(any(UserTeam.class));
    }
}
