package examples;

public class SafeAccount {
    private static int nextAccountId = 1;

    private final int accountId;
    private double balance;

    public SafeAccount(int openingBalance) {
        balance = openingBalance;
        accountId = getAndIncrementNextAccountId();
    }

    private static synchronized int getAndIncrementNextAccountId() {
        int result = nextAccountId;
        nextAccountId = nextAccountId + 1;
        return result;
    }

    public boolean withdraw(final int amount) {
        // Check to see amount > 0, throw if not
        synchronized (this) {
            if (balance >= amount) {
                balance = balance - amount;
                return true;
            }
        }
        return false;
    }

    public void deposit(final int amount) {
        // Check to see amount > 0, throw if not
        synchronized (this) {
            balance = balance + amount;
        }
    }

    public boolean transferTo(final SafeAccount other, final int amount) {
        // Check to see amount > 0, throw if not

        if (accountId == other.getAccountId()) {
            // Can't transfer to your own account
            return false;
        }

        if (accountId < other.getAccountId()) {
            synchronized (this) {
                if (balance >= amount) {
                    balance = balance - amount;
                    synchronized (other) {
                        other.deposit(amount);
                    }
                    return true;
                }
            }
            return false;
        } else {
            synchronized (other) {
                synchronized (this) {
                    if (balance >= amount) {
                        balance = balance - amount;
                        other.deposit(amount);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (this) {
            return balance;
        }
    }

    public int getAccountId() {
        return accountId;
    }
}