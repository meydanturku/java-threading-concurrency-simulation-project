package com.thread.example.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithLock {

    private int balance = 0;
    private final Lock lock = new ReentrantLock();

    public void deposit(int amount) {
        lock.lock(); // get lock
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", New Balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", New Balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient balance for withdraw: " + amount);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
