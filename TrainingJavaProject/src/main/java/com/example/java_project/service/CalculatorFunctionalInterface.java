package com.example.java_project.service;

@FunctionalInterface
public interface CalculatorFunctionalInterface {
    //  A functional interface is an interface that has exactly one abstract method.
    double calculate(double a, double b);

    // Functional interface can have default or static methods.
    default String def() {
        return "This is default method";
    }

    static String abc() {
        return "This is static method";
    }
}
