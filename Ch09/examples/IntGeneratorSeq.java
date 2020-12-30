package examples;

import lang.ISeq;

import java.util.function.Function;

public class IntGeneratorSeq implements ISeq {
    private final int current;
    private final Function<Integer, Integer> generator;

    private IntGeneratorSeq(int current, Function<Integer, Integer> generator) {
        this.current = current;
        this.generator = generator;
    }

    public static IntGeneratorSeq of(int start, Function<Integer, Integer> generator) {
        return new IntGeneratorSeq(start, generator);
    }

    @Override
    public Object first() {
        return generator.apply(current);
    }

    @Override
    public ISeq rest() {
        return new IntGeneratorSeq(generator.apply(current), generator);
    }
}
