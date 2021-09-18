package ch14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class SorterMain {
    public static void main(String[] args) {
        var main = new SorterMain();
        try {
            main.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void run() throws InterruptedException {
        var transactions = new ArrayList<Transaction>();
        var accs = new Account[] {
                new Account(1000),
                new Account(1000)};
        for (var i = 0; i < 256; i = i + 1) {
            transactions.add(Transaction.of(accs[i % 2], accs[(i + 1) % 2], 1));
            Thread.sleep(1);
        }
        Collections.shuffle(transactions);

        var sorter = new TransactionSorter(transactions);
        var pool = new ForkJoinPool(4);

        pool.invoke(sorter);

        for (var txn : sorter.getResult()) {
            System.out.println(txn);
        }
    }
}
