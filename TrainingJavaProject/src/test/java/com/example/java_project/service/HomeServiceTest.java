package com.example.java_project.service;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.Response;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAsync
class HomeServiceTest {
    @Autowired
    private HomeService homeService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks before each test
    }

    @Test
    void testCalculateAddition() {
        // Input values
        double x = 5.0;
        double y = 3.0;
        // Call the method
        Map<String, Double> result = homeService.calculateAddition(x, y);
        // Assert results
        assertEquals(8.0, result.get("Addition: "), "Addition result is incorrect");
    }

    @Test
    void testCalculateSquareArea() {
        // Input values
        double x = 5.0;
        // Call the method
        Map<String, Double> result = homeService.calculateSquareArea(x);
        // Assert results
        assertEquals(25.0, result.get("Square of x value: "), "Square result is incorrect");
    }

    private List<UserDto> createUsersDto() {
        UserDto userDto1 = new UserDto();
        userDto1.setUserName("thu");
        userDto1.setEmail("thu@a.c");
        userDto1.setPassword("123");
        userDto1.setPhoneNumber("1236547896");
        userDto1.setFirstName("thu");
        userDto1.setLastName("tran");

        UserDto userDto2 = new UserDto();
        userDto2.setUserName("thu1");
        userDto2.setEmail("thu1@a.c");
        userDto2.setPhoneNumber("1236547895");
        userDto2.setPassword("123");
        userDto2.setFirstName("thu");
        userDto2.setLastName("tran");
        return Arrays.asList(userDto1, userDto2);
    }

    @Test
    void registerUsers_AllSuccess() {
        // Act
        CompletableFuture<List<Response<UsersResponse>>> futureResult = homeService.registerUsers(createUsersDto());
        List<Response<UsersResponse>> results = futureResult.join();  // Wait for the async result

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());

        // Verify the first user's registration
        assertNotNull(results.get(0).getData());
        assertNull(results.get(0).getError());
        assertEquals("thu", results.get(0).getData().getUserName());
        assertEquals("thu@a.c", results.get(0).getData().getEmail());

        // Verify the second user's registration
        assertNotNull(results.get(1).getData());
        assertNull(results.get(1).getError());
        assertEquals("thu1", results.get(1).getData().getUserName());
        assertEquals("thu1@a.c", results.get(1).getData().getEmail());
    }

    @Test
    void registerUsers_AllFailures() {
        List<UserDto> usersDto = createUsersDto();
        // Act
        CompletableFuture<List<Response<UsersResponse>>> futureResult = homeService.registerUsers(usersDto);
        List<Response<UsersResponse>> results = futureResult.join();  // Wait for the async result

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());

        // Check failures for both users
        assertNull(results.get(0).getData());
        assertNotNull(results.get(0).getError());
        assertTrue(results.get(0).getError().contains("Failed to register user '" + usersDto.get(0).getUserName()));

        assertNull(results.get(1).getData());
        assertNotNull(results.get(1).getError());
        assertTrue(results.get(1).getError().contains("Failed to register user '" + usersDto.get(1).getUserName()));
    }
}
