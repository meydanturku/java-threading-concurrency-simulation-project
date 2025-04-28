package com.thread.example.task;

public class MessageTask implements Runnable {

    private final String threadName;
    private final String message;

    public MessageTask(String threadName, String message) {
        this.threadName = threadName;
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(threadName + " - Message: " + message);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted!");
                break;
            }
        }
    }
}