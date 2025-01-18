package com.example.java_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WebScraperServiceTest {
    @Autowired
    private WebScraperService webScraperService;

    @Test
    void testFetchTitlesConcurrently_Success() {
        // Arrange
        List<String> urls = List.of(
                "https://www.example.com",
                "https://www.wikipedia.org"
        );

        // Act
        Map<String, String> result = webScraperService.fetchTitles(urls);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Example Domain", result.get("https://www.example.com"));
        assertEquals("Wikipedia", result.get("https://www.wikipedia.org"));
    }

    @Test
    void testFetchTitlesConcurrently_EmptyList() {
        // Arrange
        List<String> urls = List.of();

        // Act
        Map<String, String> result = webScraperService.fetchTitles(urls);

        // Assert
        assertTrue(result.isEmpty(), "Result should be empty for an empty list of URLs");
    }
}
