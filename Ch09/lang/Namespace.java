package lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Namespace {
    private final Map<String, Object> bindings = new ConcurrentHashMap<>();

    private static final Map<Symbol, Namespace> namespaces = new ConcurrentHashMap<>();

    private final Symbol ns;

    private Namespace(Symbol ns) {
        this.ns = ns;
    }

    public static Namespace of(Symbol ns) {
        return new Namespace(ns);
    }

    public static Namespace findOrCreate(Symbol name){
        Namespace ns = namespaces.get(name);
        if(ns != null)
            return ns;
        Namespace newns = new Namespace(name);
        ns = namespaces.putIfAbsent(name, newns);
        return ns == null ? newns : ns;
    }
}
