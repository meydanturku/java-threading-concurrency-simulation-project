package com.thread.example.task.bankExample;

import com.thread.example.model.Account;

public class WithdrawTask implements Runnable{

    private final Account account;

    public WithdrawTask(Account account) {
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

