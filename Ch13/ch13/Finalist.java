package ch13;

import java.lang.reflect.Field;

public class Finalist {
    public final int value;

    public Finalist(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Finalist finalist = new Finalist(42);
        try {
            Field f = Finalist.class.getField("value");
            f.setAccessible(true);
            f.setInt(finalist, 21);
            System.out.println("Value: "+ finalist.value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
