package lang;

import java.util.List;

public class ArraySeq implements ISeq {
    private final int index;
    private final Object[] values;

    private ArraySeq(int index, Object[] values) {
        this.index = index;
        this.values = values;
    }

    public static ArraySeq of(List<Object> objects) {
        if (objects == null || objects.size() == 0) {
            return Empty.of();
        }
        return new ArraySeq(0, objects.toArray());
    }

    @Override
    public Object first() {
        return values[index];
    }

    @Override
    public ISeq rest() {
        if (index >= values.length - 1) {
            return Empty.of();
        }
        return new ArraySeq(index + 1, values);
    }

    public int count() {
        return values.length - index;
    }


    public static class Empty extends ArraySeq {
        private static Empty EMPTY = new Empty(0, new Object[0]);

        private Empty(int index, Object[] values) {
            super(index, values);
        }

        public static Empty of() {
            return EMPTY;
        }

        @Override
        public Object first() {
            return null;
        }

        @Override
        public ISeq rest() {
            return of();
        }

        public int count() {
            return 0;
        }
    }
}
