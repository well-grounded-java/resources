package lang;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class Utils {
    private Utils() { }

    public static IFn makeFn0(Callable c) {
        return new IFn() {
            @Override
            public Object invoke() {
                try {
                    return c.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static IFn makeFn1(Function f) {
        return new IFn() {
            @Override
            public Object invoke(Object o1) {
                return f.apply(o1);
            }
        };
    }
}
