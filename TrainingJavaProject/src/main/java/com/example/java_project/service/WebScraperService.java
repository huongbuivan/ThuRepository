package com.example.java_project.service;

import java.util.List;
import java.util.Map;

public interface WebScraperService {
    Map<String, String> fetchTitles(List<String> urls);
}
