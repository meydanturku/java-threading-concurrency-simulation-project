package com.thread.example.task;

public class CounterTask implements Runnable {

    private final String threadName;

    public CounterTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            System.out.println(threadName + " - Counter: " + counter++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted!");
                break;
            }
        }
    }
}
