package ch14;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class TransactionSorter extends RecursiveAction {
    private static final int SMALL_ENOUGH = 32; // <1>
    private final Transaction[] transactions;
    private final int start, end;
    private final Transaction[] result;

    public TransactionSorter(List<Transaction> transactions) {
        this(transactions.toArray(new Transaction[0]), 0, transactions.size());
    }

    public TransactionSorter(Transaction[] transactions) {
        this(transactions, 0, transactions.length);
    }

    public TransactionSorter(Transaction[] transactions, int startPos, int endPos) {
        start = startPos;
        end = endPos;
        this.transactions = transactions;
        result = new Transaction[this.transactions.length];
    }

    private void merge(TransactionSorter left, TransactionSorter right) {
        int i = 0;
        int lCount = 0;
        int rCount = 0;

        while (lCount < left.size() && rCount < right.size()) {
            result[i++] = (left.result[lCount].compareTo(right.result[rCount]) < 0)
                    ? left.result[lCount++]
                    : right.result[rCount++];
        }

        while (lCount < left.size()) {
            result[i++] = left.result[lCount++];
        }

        while (rCount < right.size()) {
            result[i++] = right.result[rCount++];
        }
    }

    public int size() {
        return end - start;
    }

    public Transaction[] getResult() {
        return result;
    }

    @Override
    protected void compute() {           // <2>
        if (size() < SMALL_ENOUGH) {
            System.arraycopy(transactions, start, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int mid = size() / 2;
            TransactionSorter left = new TransactionSorter(transactions, start, start + mid);
            TransactionSorter right = new TransactionSorter(transactions, start + mid, end);
            invokeAll(left, right);

            merge(left, right);
        }
    }
}