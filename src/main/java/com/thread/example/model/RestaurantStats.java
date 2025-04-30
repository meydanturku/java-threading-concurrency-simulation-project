package com.thread.example.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RestaurantStats {

    private final AtomicInteger totalCustomers = new AtomicInteger(0);
    private final AtomicInteger totalRevenue = new AtomicInteger(0);
    private final AtomicLong totalDuration = new AtomicLong(0);

    public void incrementCustomers() {
        totalCustomers.incrementAndGet();
    }

    public void addRevenue(int amount) {
        totalRevenue.addAndGet(amount);
    }

    public void addDuration(long durationMillis) {
        totalDuration.addAndGet(durationMillis);
    }

    public int getTotalCustomers() {
        return totalCustomers.get();
    }

    public int getTotalRevenue() {
        return totalRevenue.get();
    }

    public long getTotalDuration() {
        return totalDuration.get();
    }
}
