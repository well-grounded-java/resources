package lang;

public class Var implements IFn {
    private volatile Object root;

    public final Symbol sym;
    public final Namespace ns;

    private Var(Symbol sym, Namespace ns) {
        this.sym = sym;
        this.ns = ns;
    }

    private Var(Symbol sym, Namespace ns, Object root) {
        this.sym = sym;
        this.ns = ns;
        this.root = root;
    }

    public static Var of(Symbol sym) {
        return new Var(sym, Namespace.of(sym));
    }

    public static Var of(Symbol sym, Namespace ns) {
        return new Var(sym, ns);
    }

    public static Var of(Symbol sym, Object root){
        return new Var(sym, Namespace.of(sym), root);
    }

    static public class Unbound implements IFn {
        final public Var v;

        public Unbound(Var v){
            this.v = v;
        }

        @Override
        public String toString(){
            return "Unbound: " + v;
        }
    }

    public synchronized void bindRoot(Object root) {
        this.root = root;
    }

    public synchronized void unBindRoot(Object root) {
        this.root = new Unbound(this);
    }

    @Override
    public Object invoke() {
        return ((IFn)root).invoke();
    }

    @Override
    public Object invoke(Object o1) {
        return ((IFn)root).invoke(o1);
    }

    @Override
    public Object invoke(Object o1, Object o2) {
        return ((IFn)root).invoke(o1, o2);
    }

    @Override
    public Object invoke(Object o1, Object o2, Object o3) {
        return ((IFn)root).invoke(o1, o2, o3);
    }

    @Override
    public Object invoke(Object o1, Object o2, Object... others) {
        return ((IFn)root).invoke(o1, o2, others);
    }
}
