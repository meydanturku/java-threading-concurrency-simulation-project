package com.thread.example.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTask implements Runnable {

    private final String threadName;

    public TimeTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        while (true) {
            System.out.println(threadName + " - Time: " + LocalDateTime.now().format(formatter));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted!");
                break;
            }
        }
    }
}
