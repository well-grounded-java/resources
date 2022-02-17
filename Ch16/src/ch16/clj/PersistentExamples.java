package ch16.clj;

import clojure.lang.PersistentVector;
import clojure.lang.PersistentQueue;
import clojure.lang.APersistentVector;
import clojure.lang.PersistentList;
import clojure.lang.APersistentMap;
import clojure.lang.APersistentSet;

import java.util.ArrayList;
import java.util.Arrays;

public class PersistentExamples {

    public static void main(String[] args) {
        var main = new PersistentExamples();
        main.run3();
    }

    private void run() {
        var aList = new ArrayList<PersistentVector>();
        var vector = PersistentVector.EMPTY;
        for (int i = 0; i < 32; i = i + 1) {
            vector = vector.cons(i);
            aList.add(vector);
        }
        System.out.println(aList);
    }

    private void run3() {
        var vector = PersistentVector.EMPTY;
        for (int i = 0; i < 1024; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
        var fullTail = (PersistentVector.Node) (vector.root.array[0]);
        System.out.println("Full Tail: " + Arrays.toString(fullTail.array));
        System.out.println("----------------");
        for (int i = 1024; i < 1088; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
        fullTail = (PersistentVector.Node) (vector.root.array[0]);
        System.out.println("Full Tail: " + Arrays.toString(fullTail.array));
    }

    private void run2() {
        var vector = PersistentVector.EMPTY;
        for (int i = 0; i < 32; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
        System.out.println("----------------");
        for (int i=32; i < 64; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
        var earlier = (PersistentVector.Node)(vector.root.array[0]);
        System.out.println("Earlier: "+ Arrays.toString(earlier.array));
//        for (int i=64; i < 96; i = i + 1) {
//            vector = vector.cons(i);
//        }
//        System.out.println(Arrays.toString(vector.tail));
//        System.out.println(Arrays.toString(vector.root.array));
//        for (int i=96; i < 128; i = i + 1) {
//            vector = vector.cons(i);
//        }
//        System.out.println(Arrays.toString(vector.tail));
//        System.out.println(Arrays.toString(vector.root.array));
    }


}
