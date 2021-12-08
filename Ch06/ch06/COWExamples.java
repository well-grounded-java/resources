package ch06;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.HashMap;


public class COWExamples {

    public static void main(String[] args) {
        var ls = new CopyOnWriteArrayList(List.of(1, 2, 3));
        var it = ls.iterator();
        ls.add(4);
        var modifiedIt = ls.iterator();
        while (it.hasNext()) {
            System.out.println("Original: "+ it.next());
        }
        while (modifiedIt.hasNext()) {
            System.out.println("Modified: "+ modifiedIt.next());
        }
    }
}
