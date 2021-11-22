package lang;

import java.util.*;

public class MapBase extends AbstractMap implements IFn, Map {

    @Override
    public Object invoke(Object o) {
        return get(o);
    }

    // Dummy implementation - requires entrySet() to use AbstractMap as a base
    private transient Set<Map.Entry> entrySet;

    @Override
    public Set<Map.Entry> entrySet() {
        Set<Map.Entry> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    final class EntrySet extends AbstractSet<Entry> {
        @Override
        public Iterator<Entry> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
