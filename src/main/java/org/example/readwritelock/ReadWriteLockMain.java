package org.example.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain {

    public static void main (String[] args) throws InterruptedException {
        BankAccount bankAccount= new BankAccount(100);
        BankAccountService bankAccountService = new BankAccountService(bankAccount);

        Thread depositThread= new Thread()
        {
            public void run()
            {
                bankAccountService.deposit(100);
            }
        };


        Thread withdrawThread= new Thread()
        {
            public void run()
            {
                bankAccountService.withdraw(10);
            }
        };

        depositThread.start();
        withdrawThread.start();

        depositThread.join();
        withdrawThread.join();

        System.out.println("Final balance: "+bankAccountService.getBalance());

    }
}

class BankAccountService
{
    private BankAccount bankAccount;

    public BankAccountService(BankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public void deposit(int amount)
    {
        System.out.println("Depositing amount: "+amount);
        bankAccount.deposit(amount);
    }

    public void withdraw(int amount)
    {
        System.out.println("Withdrawing amount: "+amount);
        bankAccount.withdraw(amount);
    }

    public int getBalance()
    {
        return bankAccount.getBalance();
    }
}


class BankAccount
{
    private int balance;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock readLock = lock.readLock();
    private Lock writeLock= lock.writeLock();

    BankAccount(int balance)
    {
        this.balance = balance;
    }

    public int getBalance()
    {
        try {
            readLock.lock();
            return balance;
        } finally {
            readLock.unlock();
        }
    }

    public void deposit(int amount)
    {
        try {
            writeLock.lock();
            balance += amount;
        }
        finally {
            writeLock.unlock();
        }

    }

    public void withdraw(int amount)
    {
        try{
            writeLock.lock();
            balance -= amount;
        }
        finally
        {
            writeLock.unlock();
        }

    }
}
