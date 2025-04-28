package com.thread.example.task.bankExample;

import com.thread.example.model.Account;

public class DepositTask implements Runnable {

    private final Account account;

    public DepositTask(Account account) {
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