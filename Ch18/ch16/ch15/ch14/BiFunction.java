package ch13;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BiFunction<T, U, R> {

    R apply(T t, U u);

    default <V> java.util.function.BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }

    default Function<U, R> curry1(T t) {
        return u -> this.apply(t, u);
    }

    default Function<T, R> curry2(U u) {
        return t -> this.apply(t, u);
    }

}