package com.example.java_project.service;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.Response;
import com.example.java_project.dto.responses.UsersResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface HomeService {
    Map<String, Double> calculateAddition(double x, double y);
    Map<String, Double> calculateSquareArea(double x);
    CompletableFuture<UsersResponse> completedFuture(String userName);
    CompletableFuture<List<Response<UsersResponse>>> registerUsers(List<UserDto> users);
}
