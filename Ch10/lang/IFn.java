package lang;

public interface IFn {
    default Object invoke() {
        return throwArity();
    }
    default Object invoke(Object o1) {
        return throwArity();
    }
    default Object invoke(Object o1, Object o2) {
        return throwArity();
    }
    default Object invoke(Object o1, Object o2, Object o3) {
        return throwArity();
    }
    default Object invoke(Object o1, Object o2, Object... others) {
        return throwArity();
    }

    default Object throwArity(){
        throw new IllegalArgumentException("Wrong number of args passed to function: "
                + toString());
    }
}
