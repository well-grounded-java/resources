package ch13;

import java.util.function.Function;

public class PrefixerMain {
    public static void main(String[] args) {
//        var prefixer = new Prefixer();

        Function<String, Function<String, String>> prefixer = prefix -> {
            return s -> prefix +": "+ s;
        };
    }
}
