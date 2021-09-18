package ch05;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BadMapExamples {
    public static void main(String[] args) {
        var map = new HashMap<String, String>();
        var SIZE = 1_000_000;

        Runnable r1 = () -> {
          for (int i = 0; i < SIZE; i = i + 1) {
              map.put("t1" + i, "0");
          }
          System.out.println("Thread 1 done");
        };
        Runnable r2 = () -> {
            for (int i = 0; i < SIZE; i = i + 1) {
                map.put("t2" + i, "0");
            }
            System.out.println("Thread 2 done");
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException __) {
            System.err.println("Threads interrupted");
        }
        System.out.println("Count: "+ map.size());
    }
}
