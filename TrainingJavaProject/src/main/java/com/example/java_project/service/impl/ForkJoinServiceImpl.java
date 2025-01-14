package com.example.java_project.service.impl;

import com.example.java_project.service.ForkJoinService;
import com.example.java_project.utils.SumTask;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;

@Service
public class ForkJoinServiceImpl implements ForkJoinService {
    @Override
    public long calculateSum(int[] numbers) {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);

        long result = pool.invoke(task);

        pool.shutdown();
        return result;
    }
}
