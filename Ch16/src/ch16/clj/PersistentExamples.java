package ch16.clj;

import clojure.lang.PersistentVector;

import java.util.ArrayList;

public class PersistentExamples {

    public static void main(String[] args) {
        var main = new PersistentExamples();
        main.run();
    }

    private void run() {
        var aList = new ArrayList<PersistentVector>();
        var vector = PersistentVector.EMPTY;
        for (int i=0; i < 32; i = i + 1) {
            vector = vector.cons(i);
            aList.add(vector);
        }
        System.out.println(vector.root);
        for (int i=32; i < 64; i = i + 1) {
            vector = vector.cons(i);
            aList.add(vector);
        }
        System.out.println(vector.root);
        for (int i=64; i < 96; i = i + 1) {
            vector = vector.cons(i);
            aList.add(vector);
        }
        for (int i=96; i < 128; i = i + 1) {
            vector = vector.cons(i);
            aList.add(vector);
        }
    }
}
