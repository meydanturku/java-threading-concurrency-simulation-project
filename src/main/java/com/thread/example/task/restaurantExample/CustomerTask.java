package com.thread.example.task.restaurantExample;

import com.thread.example.enums.ReservationStatus;
import com.thread.example.model.RestaurantTable;

public class CustomerTask implements Runnable{

    private final RestaurantTable table;

    public CustomerTask(RestaurantTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            ReservationStatus reservationStatus = table.reserveTable(5);
            if (reservationStatus == ReservationStatus.RESERVED) {
                try {
                    table.orderFood();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    table.payBill();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " left without eating...");
            }
        }
    }
}
