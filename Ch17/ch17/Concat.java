package ch17;

import java.lang.invoke.MutableCallSite;

public class Concat {
    public static void main(String[] args) {
        String str = "foo";
        if (args.length > 0) {
            str = args[0];
        }
        System.out.println("this is my string: " + str);
    }
}
