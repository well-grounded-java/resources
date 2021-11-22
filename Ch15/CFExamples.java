package ch14;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.Arrays;

public class CFExamples {
    public static void main(String[] args) {
        var cfe = new CFExamples();
        cfe.run();
        cfe.run2();
    }

    private void run2() {
        var n = 1000;
        var future =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Starting up on thread: "+ Thread.currentThread().getName());
                    return NumberService.findPrime(n);
                });
//        future.
    }

    void run() {
        var n = 1000;
        var future =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Starting up on thread: "+ Thread.currentThread().getName());
                    return NumberService.findPrime(n);
                });
        var f2 = future.thenApply(l -> {
            System.out.println("Applying on thread: "+ Thread.currentThread().getName());
            return l * 2;
        });
        var f3 = future.thenApplyAsync(l -> {
            System.out.println("Applying async on thread: "+ Thread.currentThread().getName());
            return l * 3;
        });
        try {
            System.out.println("F2: "+ f2.get());
            System.out.println("F3: "+ f3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
