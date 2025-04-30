package com.thread.example.model;

import com.thread.example.enums.ReservationStatus;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RestaurantTable {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition tableAvailable = lock.newCondition();
    private boolean isTableOccupied = false;

    public ReservationStatus reserveTable(long timeoutSeconds) {
        boolean locked = tryAcquireLock(timeoutSeconds);
        if (!locked) {
            return ReservationStatus.FAILED_TO_LOCK;
        }

        try {
            if (!waitForTable(timeoutSeconds)) {
                return ReservationStatus.TABLE_OCCUPIED_TIMEOUT;
            }
            occupyTable();
            return ReservationStatus.RESERVED;
        } finally {
            // The lock will be released after the customer pays the bill
        }
    }

    private boolean tryAcquireLock(long timeoutSeconds) {
        try {
            return lock.tryLock(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private boolean waitForTable(long timeoutSeconds) {
        if (isTableOccupied) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for the table...");
                return tableAvailable.await(timeoutSeconds, TimeUnit.SECONDS) && !isTableOccupied;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }

    private void occupyTable() {
        isTableOccupied = true;
    }

    public void orderFood() {
        // Simulate ordering food
    }

    public void payBill() {
        try {
            // TODO: Add billing logic if needed
        } finally {
            isTableOccupied = false;
            tableAvailable.signalAll();
            lock.unlock();
        }
    }
}
