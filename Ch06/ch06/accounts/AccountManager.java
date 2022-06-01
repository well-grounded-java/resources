package ch06.accounts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AccountManager {
    private final ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();
    private volatile boolean shutdown = false;

    private final BlockingQueue<TransferTask> pending = new LinkedBlockingQueue<>();
    private final BlockingQueue<TransferTask> forDeposit = new LinkedBlockingQueue<>();
    private final BlockingQueue<TransferTask> failedInsufficientFunds = new LinkedBlockingQueue<>();

    private Thread withdrawals;
    private Thread deposits;

    public void init() {
        Runnable withdraw = () -> {
            boolean interrupted = false;
            while (!interrupted || !pending.isEmpty()) {
                try {
                    var task = pending.take();
                    var sender = task.sender();
                    if (sender.withdraw(task.amount())) {
                        forDeposit.add(task);
                    } else {
                        failedInsufficientFunds.add(task);
                    }
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            deposits.interrupt();
        };

        Runnable deposit = () -> {
            boolean interrupted = false;
            while (!interrupted || !forDeposit.isEmpty()) {
                try {
                    var task = forDeposit.take();
                    var receiver = task.receiver();
                    receiver.deposit(task.amount());
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        };

        init(withdraw, deposit);
    }

    void init(Runnable withdraw, Runnable deposit) {
        withdrawals = new Thread(withdraw);
        deposits = new Thread(deposit);
        withdrawals.start();
        deposits.start();
    }

    public Account createAccount(int balance) {
        var out = new Account(balance);
        accounts.put(out.getAccountId(), out);
        return out;
    }

    public boolean submit(TransferTask transfer) {
        if (shutdown) return false;
        pending.add(transfer);
        return true;
    }

    public void await() throws InterruptedException {
        withdrawals.join();
        deposits.join();
    }

    public void shutdown() {
        shutdown = true;
        withdrawals.interrupt();
    }
}
