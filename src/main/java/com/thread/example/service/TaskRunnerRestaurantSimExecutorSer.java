package com.thread.example.service;

import com.thread.example.model.RestaurantManager;
import com.thread.example.model.RestaurantStats;
import com.thread.example.task.restaurantExample.RestaurantSimulationExecutorSerTask;
import com.thread.example.task.restaurantExample.RestaurantStatsTask;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class TaskRunnerRestaurantSimExecutorSer {

    public String startRestaurantSimExecuteServiceTask() {

        RestaurantManager manager = new RestaurantManager(5);
        RestaurantStats stats = new RestaurantStats();
        CountDownLatch latch = new CountDownLatch(5); // wait for 5 customer threads to finish

        ExecutorService executorService = Executors.newFixedThreadPool(6); // 5 customers + 1 statistics thread

        // Submit 5 customer tasks
        for (int i = 1; i <= 5; i++) {
            String customerName = "Customer-" + i;
            RestaurantSimulationExecutorSerTask customerTask =
                    new RestaurantSimulationExecutorSerTask(manager, customerName, stats, latch);
            executorService.submit(customerTask);
        }

        // Submit the 6th thread: statistics collector
        Future<String> futureResult = executorService.submit(new RestaurantStatsTask(stats, latch));

        String report;
        try {
            report = futureResult.get();  // Wait until statistics task completes
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            report = "Failed to retrieve statistics: " + e.getMessage();
        } finally {
            executorService.shutdown();
        }

        return report;
    }
}
