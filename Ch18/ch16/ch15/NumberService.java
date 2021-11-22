package ch14;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class NumberService {
    public static long findPrime(int n) {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 42L;
    }

    void foo() {
        Stream.of().parallel();
    }
}
