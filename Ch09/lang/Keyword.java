package lang;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Keyword implements IFn {
    private static Map<Symbol, Keyword> table = new ConcurrentHashMap<>();
    private final Symbol sym;

    private Keyword(Symbol sym) {
        this.sym = sym;
    }

    public static Keyword of(Symbol sym) {
        var existing = table.get(sym);
        if (existing == null) {
            var k = new Keyword(sym);
            table.put(sym, k);
            return k;
        }
        return existing;
    }

    @Override
    public String toString() {
        return ":" + sym;
    }

    @Override
    public Object invoke(Object shouldBeMap) {
        if (shouldBeMap instanceof Map map) {
            return map.get(this);
        }
        throw new IllegalArgumentException("Expected a map, got: "+ shouldBeMap);
    }
}
