package com.example.TrainingJavaProject.controller;

import com.example.TrainingJavaProject.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;


    @PostMapping("/functional_interface")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Double> caculate(
            @RequestParam double x,
            @RequestParam double y
    ) {
        return homeService.caculate(x, y);
    }

}
