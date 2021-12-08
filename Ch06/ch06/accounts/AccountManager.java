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
    private final BlockingQueue<TransferTask> failed = new LinkedBlockingQueue<>();

    private Thread withdrawals;
    private Thread deposits;

    public void init() {
        Runnable withdraw = () -> {
            LOOP:
            while (!shutdown) {
                try {
                    var task = pending.poll(5, TimeUnit.SECONDS);
                    if (task == null) {
                        continue LOOP;
                    }
                    var sender = task.sender();
                    if (sender.withdraw(task.amount())) {
                        forDeposit.put(task);
                    } else {
                        failed.put(task);
                    }
                } catch (InterruptedException e) {
                    // Log at high criticality and proceed to next item
                }
            }
            // Drain pending queue to failed or log
        };

        Runnable deposit = () -> {
            LOOP:
            while (!shutdown) {
                TransferTask task;
                try {
                    task = forDeposit.poll(5, TimeUnit.SECONDS);
                    if (task == null) {
                        continue LOOP;
                    }
                } catch (InterruptedException e) {
                    // Log at high criticality and proceed to next item
                    continue LOOP;
                }
                var receiver = task.receiver();
                receiver.deposit(task.amount());
            }
            // Drain forDeposit queue to failed or log
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
        if (shutdown) {
            return false;
        }
        try {
            pending.put(transfer);
        } catch (InterruptedException e) {
            try {
                failed.put(transfer);
            } catch (InterruptedException x) {
                // Log at high criticality
                return false;
            }
        }
        return true;
    }

    public void await() {
        try {
            withdrawals.join();
            deposits.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void shutdown() {
        shutdown = true;
    }
}
