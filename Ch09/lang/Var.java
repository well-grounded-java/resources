package lang;

public class Var implements IFn {
    volatile Object root;

    public final Symbol sym;
    public final Namespace ns;

    private Var(Symbol sym, Namespace ns) {
        this.sym = sym;
        this.ns = ns;
    }

    public Var of(Symbol sym, Namespace ns) {
        return new Var(sym, ns);
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
