package com.thread.example.task.bankExampleWLock;

import com.thread.example.model.AccountWithLock;

public class WithdrawWLockTask implements Runnable{

    private final AccountWithLock account;

    public WithdrawWLockTask(AccountWithLock account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            account.withdraw(50);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

