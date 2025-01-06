package com.example.java_project.service.impl;

import com.example.java_project.service.CalculatorFunctionalInterface;
import com.example.java_project.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

@Service
public class HomeServiceImpl implements HomeService {
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
}