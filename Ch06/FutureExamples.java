package ch05;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureExamples {

    public static void main(String[] args) {
        Future<Long> fut = getNthPrime(1_000_000_000);
        Long result = null;

        while (result == null) {
            try {
                result = fut.get(60, TimeUnit.SECONDS);
            } catch (TimeoutException tox) {
                // Timed out - better cancel the task
                System.err.println("Task timed out, cancelling");
                fut.cancel(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            } catch (ExecutionException e) {
                e.printStackTrace();
                break;
            }

            System.out.println("Still not found the billionth prime!");
        }

        System.out.println("Found it: "+ result.longValue());
    }

    private static Future<Long> getNthPrime(int i) {
        return null;
    }
}
