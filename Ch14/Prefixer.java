package ch13;

import java.util.function.Function;

public class Prefixer implements Function<String, Function<String, String>> {
    @Override
    public Function<String, String> apply(String prefix) {
        return s -> prefix +": "+ s;
    }
}
