package ch15;

import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {
        var tomorrow = new DaySupplier();
        Stream.generate(() -> tomorrow.get())
                .limit(10)
                .forEach(System.out::println);
    }
}
