package com.example.java_project.controller;

import com.example.java_project.service.ForkJoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forkjoin")
public class ForkJoinController {
    // Using final for the fields ensures that dependencies are immutable
    // and cannot be reassigned.
    private final ForkJoinService forkJoinService;

    //  Using constructor injection instead of @Autowired
    public ForkJoinController(ForkJoinService forkJoinService) {
        this.forkJoinService = forkJoinService;
    }

    @PostMapping("/sum")
    public long calculateSum(@RequestBody int[] numbers) {
        return forkJoinService.calculateSum(numbers);
    }
}
