package com.thread.example.service;


import com.thread.example.model.Account;
import com.thread.example.model.AccountWithLock;
import com.thread.example.task.CounterTask;
import com.thread.example.task.MessageTask;
import com.thread.example.task.TimeTask;
import com.thread.example.task.bankExample.BalanceCheckTask;
import com.thread.example.task.bankExample.DepositTask;
import com.thread.example.task.bankExample.WithdrawTask;
import com.thread.example.task.bankExampleWLock.BalanceCheckWLockTask;
import com.thread.example.task.bankExampleWLock.DepositWLockTask;
import com.thread.example.task.bankExampleWLock.WithdrawWLockTask;
import org.springframework.stereotype.Service;

@Service
public class TaskRunnerService {

    public void startTasks() {
        Thread counterThread = new Thread(new CounterTask("CounterThread"));
        Thread messageThread = new Thread(new MessageTask("MessageThread", "Hello World!"));
        Thread timeThread = new Thread(new TimeTask("TimeThread"));

        counterThread.start();
        messageThread.start();
        timeThread.start();
    }

    public void startBankTasks() {
        Account account = new Account();

        Thread depositThread = new Thread(new DepositTask(account), "DepositThread");
        Thread withdrawThread = new Thread(new WithdrawTask(account), "WithdrawThread");
        Thread balanceCheckThread = new Thread(new BalanceCheckTask(account), "BalanceCheckThread");

        depositThread.start();
        withdrawThread.start();
        balanceCheckThread.start();;
    }

    public void startBankTasksWLock() {
        AccountWithLock accountWithLock = new AccountWithLock();

        Thread depositThread = new Thread(new DepositWLockTask(accountWithLock), "DepositThread");
        Thread withdrawThread = new Thread(new WithdrawWLockTask(accountWithLock), "WithdrawThread");
        Thread balanceCheckThread = new Thread(new BalanceCheckWLockTask(accountWithLock), "BalanceCheckThread");

        depositThread.start();
        withdrawThread.start();
        balanceCheckThread.start();;
    }
}

