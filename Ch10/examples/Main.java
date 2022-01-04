package examples;

import lang.Symbol;
import lang.Utils;
import lang.Var;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class Main {

//    (defn like-for [counter]
//            (loop [ctr counter]
//            (println ctr)
//            (if (< ctr 10)
//            (recur (inc ctr))
//    ctr
//   )))
    public int likeFor(int ctr) {
        LOOP: while (true) {
            System.out.println(ctr);
            if (ctr < 10) {
                ctr = ctr + 1;
                continue LOOP;
            } else {
                return ctr;
            }
        }
    }

//    (defn list-maker-fun [x f]
//            (map (fn [z] (let [w z]
//            (list w (f w))
//            )) x))
    public List<Object> listMakerFun(List<Object> x, Function<Object, Object> f) {
        return x.stream()
                .map(o -> List.of(o, f.apply(o)))
                .collect(toList());
    }

    public static void main(String[] args) {
        // (def hello "Goodnight Moon")
        var helloSym = Symbol.of("user", "hello");
        var hello = Var.of(helloSym, "Goodnight Moon");

        // (hello)
        hello.invoke();

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
