package com.thread.example.task.restaurantExample;

import com.thread.example.enums.ReservationStatus;
import com.thread.example.model.RestaurantManager;
import com.thread.example.model.RestaurantTable;

public class CustomersForExecutorSerTask implements Runnable {

    private final RestaurantManager manager;
    private final String customerName;

    public CustomersForExecutorSerTask(RestaurantManager manager, String customerName) {
        this.manager = manager;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        boolean hasReserved = false;

        for(RestaurantTable table : manager.getTables()) {
            ReservationStatus reservationStatus = table.reserveTable(3);
            if(reservationStatus == ReservationStatus.RESERVED) {
                hasReserved = true;
                try {
                    log("Reserved the table!");
                    table.orderFood();
                    Thread.sleep(2000);
                    table.payBill();
                    log("Finished meal and paid.");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                break;
            }
        }

        if(!hasReserved) {
            System.out.println(Thread.currentThread().getName() + " could not find an available table and left...");
        }
    }

    private void log(String action) {
        System.out.printf("[%s] [%s] %s%n", Thread.currentThread().getName(), customerName, action);
    }
}
