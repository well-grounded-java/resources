package ch05;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

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
            } catch (InterruptedException __) {
            }
            count.addAndGet(value);
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        var latch = new CountDownLatch(5);
        var count = new AtomicInteger();
        for (int i = 0; i < 5; i = i + 1) {
            var r  = new Counter(latch, i, count);
            new Thread(r).start();
        }
        try {
            latch.await();
            System.out.println("Total: "+ count.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
