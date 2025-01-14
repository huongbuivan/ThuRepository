package com.example.java_project.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SumTaskTest {
    private long sum(int[] numbers) {
        ForkJoinPool pool = new ForkJoinPool();
        try {
            SumTask task = new SumTask(numbers, 0, numbers.length);
            return pool.invoke(task);
        } finally {
            pool.shutdown();
        }
    }

    @Test
    void testSmallArray() {
        // Input array
        int[] numbers = {1, 2, 3, 4, 5};
        // Expected sum
        long expectedSum = 15;
        long result = sum(numbers);
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
        long result = sum(numbers);
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
        long result = sum(numbers);
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
        long result = sum(numbers);
        assertEquals(expectedSum, result);
    }
}
