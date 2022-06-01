package ch06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
