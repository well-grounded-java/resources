package ch13;

import java.util.function.Function;

public class PrefixerOld implements Function<String, Function<String, String>> {
    @Override
    public Function<String, String> apply(String prefix) {
        return new Function<String, String>() {
            @Override
            public String apply(String s) {
                return prefix +": "+ s;
            }
        };
    }
}
