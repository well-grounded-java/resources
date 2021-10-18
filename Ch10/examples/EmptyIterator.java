package examples;

import java.util.Iterator;

public class EmptyIterator implements Iterator<Object> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        throw new IllegalStateException("Can't advance an empty iterator");
    }
}
