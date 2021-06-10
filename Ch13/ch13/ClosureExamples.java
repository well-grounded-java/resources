package ch13;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ClosureExamples {

    public static void main(String[] args) {
        ClosureExamples c = new ClosureExamples();
        c.run();
        c.run2();
    }

    void run() {
        int i = 42;
        Function<String, String> f = s -> s + i;
//        i = 37;
        System.out.println(f.apply("Hello "));
    }

    void run2() {
        var i = new AtomicInteger(42);
        Function<String, String> f = s -> s + i.get();
        i.set(37);
//      i = new AtomicInteger(42);
        System.out.println(f.apply("Hello "));
    }

}
