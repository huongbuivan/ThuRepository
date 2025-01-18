package com.example.java_project.service.impl;

import com.example.java_project.service.WebScraperService;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class WebScraperServiceImpl implements WebScraperService {
    // Adjust pool size as needed
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public Map<String, String> fetchTitles(List<String> urls) {
        // Submit tasks to fetch titles of the URLs concurrently
        List<Callable<Map.Entry<String, String>>> tasks = urls.stream()
                .map(url -> (Callable<Map.Entry<String, String>>) () -> {
                    try {
                        String title = fetchTitle(url);
                        return Map.entry(url, title);
                    } catch (IOException e) {
                        return Map.entry(url, "Failed to fetch");
                    }
                })
                .toList();

        try {
            // Invoke all tasks and wait for results
            List<Future<Map.Entry<String, String>>> results = executorService.invokeAll(tasks);
            // Process the results
            return results.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            Thread.currentThread().interrupt();
                            return Map.entry("Error", "Task failed");
                        }
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task execution interrupted", e);
        } finally {
            // Shutdown the ExecutorService
            executorService.shutdown();
        }
    }

    private String fetchTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.title();
    }
}
