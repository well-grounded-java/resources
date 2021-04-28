package ch13;

import java.util.function.Function;

public interface StringApplier {
    String apply(String input, Function<String, String> f);
}
