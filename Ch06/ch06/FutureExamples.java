package ch06;

import java.util.concurrent.*;

public class FutureExamples {

    public static void main(String[] args) throws InterruptedException {
        Future<Long> fut = getNthPrime(1_000_000_000);
        try {
            long result = fut.get(1, TimeUnit.MINUTES);
            System.out.println("Found it: " + result);
        } catch (TimeoutException tox) {
            // Timed out - better cancel the task
            System.err.println("Task timed out, cancelling");
            fut.cancel(true);
        } catch (InterruptedException e) {
            fut.cancel(true);
            throw e;
        } catch (ExecutionException e) {
            fut.cancel(true);
            e.getCause().printStackTrace();
        }
    }

    private static Future<Long> getNthPrime(int i) {
        var task = new FutureTask<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
                return (long)Integer.MAX_VALUE;
            } catch (InterruptedException e) {
                System.err.println("Cancelling search");
                throw new CancellationException("interrupted");
            }
        });
        new Thread(task).start();
        return task;
    }
}
