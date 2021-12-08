package ch17;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VHAccount implements ch17.Account {
    private static final VarHandle vh;
    private volatile int balance = 0;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            vh = l.findVarHandle(VHAccount.class, "balance", int.class);
        } catch (Exception ex) { throw new Error(ex); }
    }

    @Override
    public void deposit(int amount) {
        // Check to see amount > 0, throw if not
        vh.getAndAdd(this, amount);
    }

    public VHAccount(int openingBalance) {
        balance = openingBalance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean withdraw(int amount) {
        // Check to see amount > 0, throw if not
        var currBal = balance;
        var newBal = currBal - amount;
        if (newBal >= 0) {
            return vh.compareAndSet(this, currBal, newBal);
        }
        return false;
    }

    @Override
    public boolean transferTo(Account other, int amount) {
        // Check to see amount > 0, throw if not
        if (withdraw(amount)) {
            other.deposit(amount);
            return true;
        }
        return false;
    }
}
