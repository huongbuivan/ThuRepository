package com.example.TrainingJavaProject.service.impl;

import com.example.TrainingJavaProject.service.CalculatorFunctionalInterface;
import com.example.TrainingJavaProject.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public Map<String, Double> caculate(double x, double y) {
        Map<String, Double> map = new HashMap<>();
        CalculatorFunctionalInterface add = Double::sum;
        CalculatorFunctionalInterface subtract = (a, b) -> a - b;
        CalculatorFunctionalInterface multiply = (a, b) -> a * b;

        map.put("Addition: ", add.calculate(x, y));
        map.put("Subtraction: ", subtract.calculate(x, y));
        map.put("Multiplication: ", multiply.calculate(x, y));

        // Custom functional interface
        Function<Double, Double> square = a -> a * a;
        map.put("Square of x value: ", square.apply(x));
        Predicate<Double> isEven = number -> number % 2 == 0;
        map.put("Is x value event?: ", isEven.test(x) ? 1.0 : 0.0);
        return map;
    }
}