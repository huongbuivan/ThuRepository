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
        try {
            SumTask task = new SumTask(numbers, 0, numbers.length);
            return pool.invoke(task);
        } finally {
            pool.shutdown();
        }
    }
}
