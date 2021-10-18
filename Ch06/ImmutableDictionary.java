package ch05;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class ImmutableDictionary extends Dictionary {
    private final Dictionary d;

    private ImmutableDictionary(Dictionary delegate) {
        d = delegate;
    }

    public static ImmutableDictionary of(Dictionary delegate) {
        return new ImmutableDictionary(delegate);
    }

    public static ImmutableDictionary safeOf(Dictionary other) {
        Dictionary delegate = new Dictionary();
        for (String key : other.keySet()) {
            delegate.put(key, other.get(key));
        }

        return new ImmutableDictionary(delegate);
    }

    public static ImmutableDictionary of(String... pairs) {
        if (pairs.length < 2 || pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Need pairs of strings for Dictionary");
        }
        Dictionary delegate = new Dictionary();
        for (int i = 0; i < pairs.length; i = i + 2) {
            delegate.put(pairs[i], pairs[i + 1]);
        }

        return new ImmutableDictionary(delegate);
    }

    @Override
    public int size() {
        return d.size();
    }

    @Override
    public boolean isEmpty() {
        return d.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return d.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return d.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return d.get(key);
    }

    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        return d.keySet();
    }

    @Override
    public Collection<String> values() {
        return d.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return d.entrySet();
    }
}
