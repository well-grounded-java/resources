package ch16.clj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PersistentVector {
    public final PersistentVector.Node root;
    public final Object[] tail;
    private final int cnt;

    static final AtomicReference<Thread> NOEDIT = new AtomicReference((Object)null);
    public static final Node EMPTY_NODE = new PersistentVector.Node(NOEDIT, new Object[32]);
    public static final PersistentVector EMPTY = new PersistentVector(0, EMPTY_NODE, new Object[0]);

    PersistentVector(int cnt, PersistentVector.Node root, Object[] tail) {
        this.cnt = cnt;
        this.root = root;
        this.tail = tail;
    }

    public static class Node implements Serializable {
        public final transient AtomicReference<Thread> edit;
        public final Object[] array;

        public Node(AtomicReference<Thread> edit, Object[] array) {
            this.edit = edit;
            this.array = array;
        }

        Node(AtomicReference<Thread> edit) {
            this.edit = edit;
            this.array = new Object[32];
        }
    }

    public static PersistentVector create(List list) {
        int size = list.size();
        if (size <= 32) {
            return new PersistentVector(size, EMPTY_NODE, list.toArray());
        } else {
            var ret = EMPTY;

            for(int i = 0; i < size; ++i) {
                ret = ret.cons(list.get(i));
            }

            return ret;
        }
    }

    public static PersistentVector create(Iterable items) {
        if (items instanceof ArrayList) {
            return create((List)((ArrayList)items));
        } else {
            PersistentVector ret = EMPTY;

            for(Iterator iter = items.iterator(); iter.hasNext(); ) {
                ret = ret.cons(iter.next());
            }

            return ret;
        }
    }

    public PersistentVector cons(Object val) {
        if (this.cnt < 32) {
            Object[] newTail = new Object[this.tail.length + 1];
            System.arraycopy(this.tail, 0, newTail, 0, this.tail.length);
            newTail[this.tail.length] = val;
            return new PersistentVector( this.cnt + 1, this.root, newTail);
        } else {
            PersistentVector.Node tailnode = new PersistentVector.Node(this.root.edit, this.tail);
            PersistentVector.Node newroot = new PersistentVector.Node(this.root.edit);
                newroot.array[0] = this.root;
                newroot.array[1] = newPath(this.root.edit, 5, tailnode);

            return new PersistentVector(1, newroot, new Object[]{val});
        }
    }

    private static PersistentVector.Node newPath(AtomicReference<Thread> edit, int level, PersistentVector.Node node) {
        if (level == 0) {
            return node;
        } else {
            PersistentVector.Node ret = new PersistentVector.Node(edit);
            ret.array[0] = newPath(edit, level - 5, node);
            return ret;
        }
    }

}
