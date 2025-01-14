package com.example.java_project.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SumTaskTest {
    @Test
    void testSmallArray() {
        // Input array
        int[] numbers = {1, 2, 3, 4, 5};

        // Expected sum
        long expectedSum = 15;

        // Execute SumTask
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        // Assert
        assertEquals(expectedSum, result);
    }

    @Test
    void testLargeArray() {
        // Input array
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1; // Fill with 1 to 100
        }

        // Expected sum: (n * (n + 1)) / 2 for first n natural numbers
        long expectedSum = 5050;

        // Execute SumTask
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        // Assert
        assertEquals(expectedSum, result);
    }

    @Test
    void testEmptyArray() {
        // Input array
        int[] numbers = {};

        // Expected sum
        long expectedSum = 0;

        // Execute SumTask
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        // Assert
        assertEquals(expectedSum, result);
    }

    @Test
    void testSingleElementArray() {
        // Input array
        int[] numbers = {42};

        // Expected sum
        long expectedSum = 42;

        // Execute SumTask
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        assertEquals(expectedSum, result);
    }
}
