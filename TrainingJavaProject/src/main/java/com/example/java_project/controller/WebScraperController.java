package com.example.java_project.controller;

import com.example.java_project.service.WebScraperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("web_scraper")
public class WebScraperController {
    private final WebScraperService webScraperService;

    public WebScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }

    @PostMapping("/titles")
    public Map<String, String> fetchTitles(@RequestBody List<String> urls) {
        return webScraperService.fetchTitles(urls);
    }
}
