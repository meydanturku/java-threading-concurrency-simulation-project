package com.thread.example.task.bankExampleWLock;

import com.thread.example.model.AccountWithLock;

public class BalanceCheckWLockTask implements Runnable {

    private final AccountWithLock account;

    public BalanceCheckWLockTask(AccountWithLock account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " checked balance: " + account.getBalance());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
