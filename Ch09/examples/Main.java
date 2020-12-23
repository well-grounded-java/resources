package examples;

import lang.Symbol;
import lang.Utils;
import lang.Var;

public class Main {
    public static void main(String[] args) {
        // (def pi 3.14)
        var piSym = Symbol.of("user", "pi");
        var pi = Var.of(piSym, Double.valueOf(3.14));

        // (pi)
        pi.invoke(); // throws as pi is a constant

        // (def constZero (fn [] 0))
        var czSym = Symbol.of("user", "constZero");
        var constZero = Var.of(czSym, Utils.makeFn0(() -> 0));

        // (constZero)
        var ret = constZero.invoke();
        System.out.println(ret);

        // (constZero 1)
        System.out.println(constZero.invoke(1)); // throws

        // (def ident (fn [x] x))
        var identSym = Symbol.of("user", "ident");
        var ident = Var.of(identSym, Utils.makeFn1(x -> x));

        // (ident)
        System.out.println(ident.invoke());  // throws

        // (ident 1)
        System.out.println(ident.invoke(1));

        // (ident "foo")
        System.out.println(ident.invoke("foo"));
    }
}
