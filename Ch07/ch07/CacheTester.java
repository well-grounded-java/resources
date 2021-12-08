package ch07;

public class CacheTester {
    private final int ARR_SIZE = 2 * 1024 * 1024;
    private final int[] arr = new int[ARR_SIZE];

    private void doLoop2() {
        for (var i = 0; i < arr.length; i = i + 1) {
            arr[i]++; // #A
        }
    }

    private void doLoop1() {
        for (var i = 0; i < arr.length; i = i + 16) {
            arr[i]++; // #B
        }
    }

    private void run() {
        for (var i = 0; i < 10_000; i = i + 1) { // #C
            doLoop1();
            doLoop2();
        }

        for (var i = 0; i < 100; i = i + 1) {
            long t0 = System.nanoTime();
            doLoop1();
            long t1 = System.nanoTime();
            doLoop2();
            long t2 = System.nanoTime();

            long el = t1 - t0;
            long el2 = t2 - t1;

            System.out.println("Loop1: "+ el +" nanos ; Loop2: "+ el2);
        }
    }

    public static void main(String[] args) {
        var ct = new CacheTester();
        ct.run();
    }
}
