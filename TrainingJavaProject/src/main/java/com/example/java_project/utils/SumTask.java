package com.example.java_project.utils;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
    private final int[] numbers;
    private final int start;
    private final int end;
    // Threshold: The size of the task below which computation is performed sequentially.
    private static final int THRESHOLD = 10;

    public SumTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // If the task is small enough, compute sequentially.
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            // Otherwise, split the task into two subtasks.
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);
            // Fork left task
            leftTask.fork();
            // Compute right task directly
            long rightResult = rightTask.compute();
            // Join the result of the left task
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}
