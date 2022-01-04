import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyUnsafe {
    public static void main(String[] args) {
        MyUnsafe self = new MyUnsafe();
        try {
            self.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe);
    }
}
