package com.example.TrainingJavaProject;

import com.example.TrainingJavaProject.config.SecurityTestConfig;
import com.example.TrainingJavaProject.controller.HomeController;
import com.example.TrainingJavaProject.dto.request.UserDto;
import com.example.TrainingJavaProject.dto.responses.UserImmutability;
import com.example.TrainingJavaProject.service.HomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
@Import(SecurityTestConfig.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;

    @Test
    void testDataTypes() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("thu");
        userDto.setEmail("thu@a.c");
        userDto.setPassword("123");
        userDto.setFirstName("thu");
        userDto.setLastName("tran");

        // Expected UserImmutability object
        UserImmutability expectedUserImmutability = new UserImmutability(
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName()
        );

        // Mock the request and verify response
        mockMvc.perform(post("/home/data_types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(expectedUserImmutability.getUserName()))
                .andExpect(jsonPath("$.email").value(expectedUserImmutability.getEmail()))
                .andExpect(jsonPath("$.firstName").value(expectedUserImmutability.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expectedUserImmutability.getLastName()));
    }

    @Test
    void testLargeObjectCreation() {
        // Adjust the number to create a large number of objects.
        final int NUM_OBJECTS = 2; // 1 million objects, Stored in stack (primitive value)
        // `objects` is a reference stored in the stack; the `MyObject` instance is stored in the heap
        MyObject[] objects = new MyObject[NUM_OBJECTS];

        // Create instances of MyObject.
        for (int i = 0; i < NUM_OBJECTS; i++) {
            objects[i] = new MyObject();
        }

        // Perform garbage collection to see how it behaves.
        System.gc(); // Suggests that garbage collection should run.

        // Optionally, check if all objects were created.
        assertNotNull("Objects should be created successfully.", objects[0]);
    }

    static class MyObject {
        private final int[] data = new int[1000]; // Array to consume memory. Stored in the heap as part of the object
    }
}
