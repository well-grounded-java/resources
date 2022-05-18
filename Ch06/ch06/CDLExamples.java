package ch06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class CDLExamples {
    public static class Counter implements Runnable {
        private final CountDownLatch latch;
        private final int value;
        private final AtomicInteger count;

        public Counter(CountDownLatch latch, int value, AtomicInteger count) {
            this.latch = latch;
            this.value = value;
            this.count = count;
        }

        @Override
        public void run() {
            LockSupport.parkNanos(100_000_000);
            count.addAndGet(value);
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var latch = new CountDownLatch(5);
        var count = new AtomicInteger();
        for (int i = 0; i < 5; i = i + 1) {
            var r  = new Counter(latch, i, count);
            new Thread(r).start();
        }
        latch.await();
        System.out.println("Total: "+ count.get());
    }
}
