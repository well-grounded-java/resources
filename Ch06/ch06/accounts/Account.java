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
        lock.newCondition();
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

        if (accountId < other.getAccountId()) {
            lock.lock();
            try {
                if (balance >= amount) {
                    balance = balance - amount;
                    other.lock.lock();
                    try {
                        other.deposit(amount);
                    } finally {
                        other.lock.unlock();
                    }
                    return true;
                }
            } finally {
                lock.unlock();
            }
            return false;
        } else {
            other.lock.lock();
            try {
                lock.lock();
                try {
                    if (balance >= amount) {
                        balance = balance - amount;
                        other.deposit(amount);
                        return true;
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                other.lock.unlock();
            }
            return false;
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