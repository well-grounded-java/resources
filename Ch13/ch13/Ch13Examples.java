package ch13;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Function;

public class Ch13Examples {

    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(1984, Month.APRIL, 13);
        LocalDate dec = ld.withMonth(12);
        System.out.println(dec);
        System.out.println(tailrecFactorial(6));

        var f = makePrefixer("NaNa");
        System.out.println(doubleApplier("Batman", f));
    }

    public static long simpleFactorial(long n) {
        if (n <= 0) {
            return 1;
        } else {
            return n * simpleFactorial(n - 1);
        }
    }

    private static long helpFact(long i, long j) {
        if (i == 0) {
            return j;
        }
        return helpFact(i - 1, i * j);
    }

    public static long tailrecFactorial(long n) {
        if (n <= 0) {
            return 1;
        }
        return helpFact(n, 1);
    }

    public static Function<String, String> makePrefixer(String prefix) {
        return s -> prefix +": "+ s;
    }

    public static String doubleApplier(String input, Function<String, String> f) {
        return f.andThen(f).apply(input);
//        return f.apply(f.apply(input));
    }

    public static Function<String, String> makePrefixerOld(String prefix) {
        return new Function<String, String>() {
            @Override
            public String apply(String s) {
                return prefix +": "+ s;
            }
        };
    }

    public static class DoubleApplier implements Function<String, Function<String, String>> {
        @Override
        public Function<String, String> apply(String s) {
            return null;
        }
    }
}
