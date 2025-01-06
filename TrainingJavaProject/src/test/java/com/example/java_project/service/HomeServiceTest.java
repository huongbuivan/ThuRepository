package com.example.java_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HomeServiceTest {
    @Autowired
    private HomeService homeService;

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
}
