package com.thread.example.model;

import lombok.Data;

@Data
public class Account {

    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", New Balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance for withdraw: " + amount);
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}

