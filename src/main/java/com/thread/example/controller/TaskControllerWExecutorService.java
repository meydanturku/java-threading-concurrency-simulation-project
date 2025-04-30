package com.thread.example.controller;

import com.thread.example.service.TaskRunnerExecutorService;
import com.thread.example.service.TaskRunnerRestaurantSimExecutorSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threads/executor")
public class TaskControllerWExecutorService {

    private final TaskRunnerExecutorService taskRunnerExecutorService;
    private final TaskRunnerRestaurantSimExecutorSer taskRunnerRestaurantSimExecutorSer;

    public TaskControllerWExecutorService(TaskRunnerExecutorService taskRunnerExecutorService,
                                          TaskRunnerRestaurantSimExecutorSer taskRunnerRestaurantSimExecutorSer) {
        this.taskRunnerExecutorService = taskRunnerExecutorService;
        this.taskRunnerRestaurantSimExecutorSer = taskRunnerRestaurantSimExecutorSer;
    }

    @GetMapping("/start")
    public String startRestaurantThread() {
        taskRunnerExecutorService.startRestaurantExecuteServiceTask();
        return "Restaurant Thread başlatıldı!";
    }

    @GetMapping("/restaurant/simulation")
    public String startRestaurantSimilation() {
        return taskRunnerRestaurantSimExecutorSer.startRestaurantSimExecuteServiceTask();
    }

}

