package com.example.java_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  // Enable asynchronous processing
public class TrainingJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingJavaProjectApplication.class, args);
    }

}
