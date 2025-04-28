package com.thread.example.task.bankExample;

import com.thread.example.model.Account;

public class BalanceCheckTask implements Runnable {

    private final Account account;

    public BalanceCheckTask(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " checked balance: " + account.getBalance());
            try {
                Thread.sleep(2000); // Poll the balance every 2 seconds
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
