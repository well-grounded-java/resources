package ch14;


import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class TransactionSorter extends RecursiveAction {
    private static final int SMALL_ENOUGH = 32; // <1>
    private final Transaction[] Transactions;
    private final int start, end;
    private final Transaction[] result;

    public TransactionSorter(Transaction[] Transactions_) {
        this(Transactions_, 0, Transactions_.length);
    }

    public TransactionSorter(Transaction[] upds_, int startPos_, int endPos_) {
        start = startPos_;
        end = endPos_;
        Transactions = upds_;
        result = new Transaction[Transactions.length];
    }

    private void merge(TransactionSorter left_, TransactionSorter right_) {
        int i = 0;
        int lCt = 0;
        int rCt = 0;

        while (lCt < left_.size() && rCt < right_.size()) {
            result[i++] = (left_.result[lCt].compareTo(right_.result[rCt]) < 0)
                    ? left_.result[lCt++]
                    : right_.result[rCt++];
        }

        while (lCt < left_.size()) {
            result[i++] = left_.result[lCt++];
        }

        while (rCt < right_.size()) {
            result[i++] = right_.result[rCt++];
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
            System.arraycopy(Transactions, start, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int mid = size() / 2;
            TransactionSorter left = new TransactionSorter(Transactions, start, start + mid);
            TransactionSorter right = new TransactionSorter(Transactions, start + mid, end);
            invokeAll(left, right);

            merge(left, right);
        }
    }
}