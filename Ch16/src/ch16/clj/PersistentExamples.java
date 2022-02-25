package ch16.clj;

import clojure.lang.PersistentVector;

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
        for (int i = 0; i < 1088; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
        System.out.println();

        var vRA0 = (PersistentVector.Node) (vector.root.array[0]);
        var vRA1 = (PersistentVector.Node) (vector.root.array[1]);
        System.out.println("r.a[0] : " + Arrays.toString(vRA0.array));
        System.out.println("r.a[1] : " + Arrays.toString(vRA1.array));
        System.out.println();

        var vRA0A0 = (PersistentVector.Node)(((PersistentVector.Node)vRA0).array[0]);
        var vRA0A31 = (PersistentVector.Node)(((PersistentVector.Node)vRA0).array[31]);
        var vRA1A0 = (PersistentVector.Node)(((PersistentVector.Node)vRA1).array[0]);
        System.out.println("r.a[0].a[0] : " + Arrays.toString(vRA0A0.array));
        System.out.println("r.a[0].a[31] : " + Arrays.toString(vRA0A31.array));
        System.out.println("r.a[1].a[0] : " + Arrays.toString(vRA1A0.array));
    }

    private void run2() {
        var vector = PersistentVector.EMPTY;
        for (int i = 0; i < 32; i = i + 1) {
            vector = vector.cons(i);
        }
//        System.out.println(Arrays.toString(vector.tail));
//        System.out.println(Arrays.toString(vector.root.array));
//        System.out.println("----------------");
        for (int i=32; i < 64; i = i + 1) {
            vector = vector.cons(i);
        }
//        System.out.println(Arrays.toString(vector.tail));
//        System.out.println(Arrays.toString(vector.root.array));
//        var earlier = (PersistentVector.Node)(vector.root.array[0]);
//        System.out.println("Earlier: "+ Arrays.toString(earlier.array));
        for (int i=64; i < 96; i = i + 1) {
            vector = vector.cons(i);
        }
        System.out.println(Arrays.toString(vector.tail));
        System.out.println(Arrays.toString(vector.root.array));
//        for (int i=96; i < 128; i = i + 1) {
//            vector = vector.cons(i);
//        }
//        System.out.println(Arrays.toString(vector.tail));
//        System.out.println(Arrays.toString(vector.root.array));
    }


}
