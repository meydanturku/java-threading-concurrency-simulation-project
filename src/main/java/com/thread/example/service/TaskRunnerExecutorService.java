package com.thread.example.service;


import com.thread.example.model.RestaurantManager;
import com.thread.example.task.restaurantExample.CustomersForExecutorSerTask;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TaskRunnerExecutorService {

    public void startRestaurantExecuteServiceTask() {

        RestaurantManager manager = new RestaurantManager(5); // there are 5 table

        ExecutorService executorService = Executors.newFixedThreadPool(5); // we opened thread pool which size is 5

        for(int i = 1; i <= 10; i++) {
            String customerName = "Customer-" + i;
            CustomersForExecutorSerTask customers = new CustomersForExecutorSerTask(manager, customerName);
            executorService.submit(customers); // rather than execute thread, mission is given to executorService
        }

        executorService.shutdown(); // executorService is not receive new mission

        try{
            if(executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

