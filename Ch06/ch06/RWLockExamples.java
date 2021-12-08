package ch06;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockExamples {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private void run() {
        lock.readLock();
    }

    public static void main(String[] args) {
        var rwl = new RWLockExamples();
        rwl.run();
    }
}
