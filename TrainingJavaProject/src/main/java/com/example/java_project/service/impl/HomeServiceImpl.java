package com.example.java_project.service.impl;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.Response;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.models.User;
import com.example.java_project.repository.UsersRepository;
import com.example.java_project.service.CalculatorFunctionalInterface;
import com.example.java_project.service.HomeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.DoubleUnaryOperator;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Override
    public Map<String, Double> calculateAddition(double x, double y) {
        Map<String, Double> map = new HashMap<>();
        CalculatorFunctionalInterface add = Double::sum;
        map.put("Addition: ", add.calculate(x, y));
//        CalculatorFunctionalInterface subtract = (a, b) -> a - b;
//        CalculatorFunctionalInterface multiply = (a, b) -> a * b;
//        map.put("Subtraction: ", subtract.calculate(x, y));
//        map.put("Multiplication: ", multiply.calculate(x, y));
//        Predicate<Double> isEven = number -> number % 2 == 0;
//        map.put("Is x value event?: ", isEven.test(x) ? 1.0 : 0.0);
        return map;
    }

    @Override
    public Map<String, Double> calculateSquareArea(double x) {
        Map<String, Double> map = new HashMap<>();
        // Custom functional interface
        DoubleUnaryOperator square = a -> a * a;
        map.put("Square of x value: ", square.applyAsDouble(x));
        return map;
    }

    @Async
    public CompletableFuture<UsersResponse> completedFuture(String userName) {
        // Simulate delay to mimic long-running operation (e.g., database query)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn(e.getMessage());
        }
        // Retrieve user from PostgreSQL asynchronously
        User user = usersRepository.findByUserName(userName);
        UsersResponse usersResponse = user != null ? user.mapToUsersResponse(): new UsersResponse();
        return CompletableFuture.completedFuture(usersResponse);
    }

    private static String getErrorMessage(UserDto user, Exception e) {
        String message = e.getMessage();
        StringBuilder error = new StringBuilder("Failed to register user '" + user.getUserName());
        // Handle unique constraint violation for Users table
        if (message.contains("username_key")) {
            error.append("': Username is already taken.");
        } else if (message.contains("email_key")) {
            error.append("': Email is already registered.");
        } else {
            error.append("': Duplicate entry.");
        }
        return error.toString();
    }

    @Async
    public CompletableFuture<Response<UsersResponse>> registerUser(UserDto user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User userRequest = new User();
        userRequest.setUserName(user.getUserName());
        userRequest.setPassword(hashedPassword);
        userRequest.setEmail(user.getEmail());
        userRequest.setPhoneNumber(user.getPhoneNumber());
        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        try {
            User savedUser = usersRepository.save(userRequest);
            return CompletableFuture.completedFuture(new Response<>(savedUser.mapToUsersResponse(), null));
        } catch (Exception e) {
            logger.error("Failed to register user: {}", user.getUserName(), e);
            String error = getErrorMessage(user, e);
            return CompletableFuture.completedFuture(new Response<>(null, error));
        }
    }

    @Async
    public CompletableFuture<List<Response<UsersResponse>>> registerUsers(List<UserDto> users) {
        List<CompletableFuture<Response<UsersResponse>>> futures = users.stream()
                .map(this::registerUser)
                .toList();

        // Combine all CompletableFuture<User> into a single CompletableFuture<List<User>>
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        return allFutures.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .toList());
    }
}