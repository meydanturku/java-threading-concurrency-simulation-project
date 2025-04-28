package com.thread.example.task.bankExampleWLock;

import com.thread.example.model.AccountWithLock;

public class DepositWLockTask implements Runnable {

    private final AccountWithLock account;

    public DepositWLockTask(AccountWithLock account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            account.deposit(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}