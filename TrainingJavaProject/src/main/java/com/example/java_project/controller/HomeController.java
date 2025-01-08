package com.example.java_project.controller;

import com.example.java_project.dto.request.UserDto;
import com.example.java_project.dto.responses.Response;
import com.example.java_project.dto.responses.UserImmutability;
import com.example.java_project.dto.responses.UsersResponse;
import com.example.java_project.service.HomeService;
import com.example.java_project.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/home")
public class HomeController {
    // Using final for the fields ensures that dependencies are immutable
    // and cannot be reassigned.
    private final HomeService homeService;

    //  Using constructor injection instead of @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("/functional_interface")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Double> calculateAddition(
            @RequestParam double x,
            @RequestParam double y
    ) {
        return homeService.calculateAddition(x, y);
    }

    @PostMapping("/data_types")
    @ResponseStatus(HttpStatus.OK)
    public UserImmutability getUserImmutability(@Valid @RequestBody UserDto user) {
        return new UserImmutability(user.getUserName(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    @PostMapping("/completable_future")
    @ResponseStatus(HttpStatus.OK)
    public UsersResponse completableFuture(@RequestParam String userName) throws ExecutionException, InterruptedException {
        CompletableFuture<UsersResponse> userFuture = homeService.completedFuture(userName);

        // Wait for the result asynchronously
        // .get() will block the current thread, but it is asynchronous at the service level.
        return userFuture.get();
    }

    @PostMapping("/completable_future_user")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> registerUsers(@RequestBody List<UserDto> users) {
        return homeService.registerUsers(users)
                .thenApply(results -> {
                    Map<String, Object> response = new HashMap<>();
                    List<UsersResponse> successes = results.stream()
                            .filter(result -> result.getError() == null)
                            .map(Response::getData)
                            .toList();
                    List<String> errors = results.stream()
                            .map(Response::getError)
                            .filter(Objects::nonNull)
                            .toList();

                    response.put("successes", successes);
                    response.put("errors", errors);
                    return ResponseEntity.ok(response);
                });
    }
}
