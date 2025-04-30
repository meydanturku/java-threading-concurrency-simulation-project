package com.thread.example.task.restaurantExample;

import com.thread.example.model.RestaurantStats;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class RestaurantStatsTask implements Callable<String> {

    private final RestaurantStats stats;
    private final CountDownLatch latch;

    public RestaurantStatsTask(RestaurantStats stats, CountDownLatch latch) {
        this.stats = stats;
        this.latch = latch;
    }

    @Override
    public String call() {
        try {
            latch.await(); // wait until all customers finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Raporlama işlemi yarıda kesildi.";
        }

        // Raporu derle
        String report = "\n🧾 Restaurant Statistics:\n" +
                "👥 Total Customers: " + stats.getTotalCustomers() + "\n" +
                "💵 Total Revenue: " + stats.getTotalRevenue() + " TL\n" +
                "🕒 Total Duration: " + stats.getTotalDuration() + " ms\n";

        return report;
    }
}
