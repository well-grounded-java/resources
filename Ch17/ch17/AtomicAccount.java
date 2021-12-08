package ch17;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class AtomicAccount implements ch17.Account {
    private static final Unsafe unsafe;
    private static final long balanceOffset;

    private volatile int balance = 0;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            balanceOffset = unsafe.objectFieldOffset
                    (AtomicAccount.class.getDeclaredField("balance"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public AtomicAccount(int openingBalance) {
        balance = openingBalance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        // Check to see amount > 0, throw if not
        unsafe.getAndAddInt(this, balanceOffset, amount);
    }

    @Override
    public boolean withdraw(int amount) {
        // Check to see amount > 0, throw if not
        var currBal = balance;
        var newBal = currBal - amount;
        if (newBal >= 0) {
            if (unsafe.compareAndSwapInt(this, balanceOffset, currBal, newBal)) {
                return true;
            }
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
