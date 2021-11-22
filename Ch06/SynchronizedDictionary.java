package ch05;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class SynchronizedDictionary extends Dictionary {
    private final Dictionary d;

    private SynchronizedDictionary(Dictionary delegate) {
        d = delegate;
    }

    public static SynchronizedDictionary of(Dictionary delegate) {
        return new SynchronizedDictionary(delegate);
    }

    @Override
    public synchronized int size() {
        return d.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return d.isEmpty();
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return d.containsKey(key);
    }

    @Override
    public synchronized boolean containsValue(Object value) {
        return d.containsValue(value);
    }

    @Override
    public synchronized String get(Object key) {
        return d.get(key);
    }

    @Override
    public synchronized String put(String key, String value) {
        return d.put(key, value);
    }

    @Override
    public synchronized String remove(Object key) {
        return d.remove(key);
    }

    @Override
    public synchronized void putAll(Map<? extends String, ? extends String> m) {
        d.putAll(m);
    }

    @Override
    public synchronized void clear() {
        d.clear();
    }

    @Override
    public synchronized Set<String> keySet() {
        return d.keySet();
    }

    @Override
    public synchronized Collection<String> values() {
        return d.values();
    }

    @Override
    public synchronized Set<Entry<String, String>> entrySet() {
        return d.entrySet();
    }
}
