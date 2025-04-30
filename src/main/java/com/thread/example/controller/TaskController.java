package com.thread.example.controller;


import com.thread.example.service.TaskRunnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threads")
public class TaskController {

    private final TaskRunnerService taskRunnerService;

    public TaskController(TaskRunnerService taskRunnerService) {
        this.taskRunnerService = taskRunnerService;
    }

    @GetMapping("/start")
    public String startThreads() {
        taskRunnerService.startTasks();
        return "Thread'ler başlatıldı!";
    }

    @GetMapping("/bank/start")
    public String startBankThreads() {
        taskRunnerService.startBankTasks();
        return "Bank Thread'ler başlatıldı!";
    }

    @GetMapping("/bank/wlock/start")
    public String startBankWLockThreads() {
        taskRunnerService.startBankTasksWLock();
        return "Bank Thread'ler başlatıldı!";
    }

    @GetMapping("/restaurant/start")
    public String startRestaurantThread() {
        taskRunnerService.startRestaurantTask();
        return "Restaurant Thread başlatıldı!";
    }
}
