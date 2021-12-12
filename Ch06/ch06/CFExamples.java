package ch06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFExamples {

    public static void main(String[] args) {
        var numF = new CompletableFuture<Integer>();

        new Thread(() -> {
            int num = (int) (Integer.MAX_VALUE * Math.random());
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numF.complete(num);
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numF.complete(42);

        try {
            System.out.println(numF.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
