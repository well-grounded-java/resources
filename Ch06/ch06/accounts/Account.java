package ch06.accounts;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static AtomicInteger nextAccountId = new AtomicInteger(1);

    private final int accountId;
    private double balance;
    private final Lock lock = new ReentrantLock();

    public Account(int openingBalance) {
        balance = openingBalance;
        accountId = nextAccountId.getAndIncrement();
    }

    public boolean withdraw(final int amount) {
        // Check to see amount > 0, throw if not
        lock.lock();
        try {
            if (balance >= amount) {
                balance = balance - amount;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public void deposit(final int amount) {
        // Check to see amount > 0, throw if not
        lock.lock();
        try {
            balance = balance + amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean transferTo(final Account other, final int amount) {
        // Check to see amount > 0, throw if not
        if (accountId == other.getAccountId()) {
            // Can't transfer to your own account
            return false;
        }

        var firstLock = accountId < other.getAccountId() ?
                lock : other.lock;
        var secondLock = firstLock == lock ? other.lock : lock;

        firstLock.lock();
        try {
            secondLock.lock();
            try {
                if (balance >= amount) {
                    balance = balance - amount;
                    other.deposit(amount);
                    return true;
                }
                return false;
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", lock=" + lock +
                '}';
    }
}