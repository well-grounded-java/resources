package ch02;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflBytecodeName {

    public static void main(String[] args) {
        ReflBytecodeName refl = new ReflBytecodeName();
        try {
            refl.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void run() throws Exception {
        // Exception handling elided
        Class<?> clz = Class.forName("sun.invoke.util.BytecodeName");
        Method method = clz.getDeclaredMethod("parseBytecodeName", String.class);
        Object res = method.invoke(null, "java/lang/String");
        System.out.println(Arrays.toString((Object[])res));
    }
}
