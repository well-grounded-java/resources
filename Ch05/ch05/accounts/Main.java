package ch05.accounts;

public class Main {

    public static void main(String[] args) {
        var manager = new AccountManager();
        manager.init();
        var acc1 = manager.createAccount(1000);
        var acc2 = manager.createAccount(20_000);

        var transfer = new TransferTask(acc1, acc2, 100);
        manager.submit(transfer);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(acc1);
        System.out.println(acc2);
        manager.shutdown();
        manager.await();
    }
}
