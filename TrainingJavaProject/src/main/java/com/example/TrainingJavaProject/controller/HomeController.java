package com.example.TrainingJavaProject.controller;

import com.example.TrainingJavaProject.dto.request.UserDto;
import com.example.TrainingJavaProject.dto.responses.UserImmutability;
import com.example.TrainingJavaProject.service.HomeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

}
