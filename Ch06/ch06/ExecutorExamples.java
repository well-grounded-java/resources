package ch06;

import java.util.concurrent.Executors;

public class ExecutorExamples {

    public static void main(String[] args) {
        var pool = Executors.newSingleThreadExecutor();
        Runnable hello = () -> System.out.println("Hello world");
        pool.submit(hello);
    }
}
