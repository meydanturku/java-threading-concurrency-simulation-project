package com.thread.example.task.restaurantExample;

import com.thread.example.enums.ReservationStatus;
import com.thread.example.model.RestaurantManager;
import com.thread.example.model.RestaurantStats;
import com.thread.example.model.RestaurantTable;

import java.util.concurrent.CountDownLatch;

public class RestaurantSimulationExecutorSerTask implements Runnable {

    private final RestaurantManager restaurantManager;
    private final String customerName;
    private final RestaurantStats stats;
    private final CountDownLatch latch;

    public RestaurantSimulationExecutorSerTask(RestaurantManager restaurantManager, String customerName,
                                               RestaurantStats stats, CountDownLatch latch) {
        this.restaurantManager = restaurantManager;
        this.customerName = customerName;
        this.stats = stats;
        this.latch = latch;
    }

    @Override
    public void run() {
        boolean hasReserved = false;

        for (RestaurantTable table : restaurantManager.getTables()) {
            ReservationStatus reservationStatus = table.reserveTable(3);
            if (reservationStatus == ReservationStatus.RESERVED) {
                hasReserved = true;
                try {
                    log("Table reserved");
                    long start = System.currentTimeMillis();

                    table.orderFood();
                    log("Food order placed");
                    Thread.sleep(2000);
                    log("Customer is eating...");
                    table.payBill();
                    log("Bill paid. Customer left the restaurant.");

                    long end = System.currentTimeMillis();
                    long duration = end - start;

                    stats.addDuration(duration);
                    stats.addRevenue(150);
                    stats.incrementCustomers();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown(); // signal that this customer has finished
                }
                break; // no need to check other tables after reservation
            }
        }

        if (!hasReserved) {
            log("Reservation failed. Customer left the restaurant.");
        }
    }

    private void log(String action) {
        System.out.printf("[%s] [%s] %s%n", Thread.currentThread().getName(), customerName, action);
    }
}
