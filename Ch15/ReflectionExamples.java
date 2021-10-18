package ch15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExamples {

    public static void main(String[] args) {
        var m = new ReflectionExamples();
        try {
            m.run();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = ReflectionExamples.class;
        Method m = clazz.getMethod("toString");
        Object ret = m.invoke(this);
        System.out.println(ret);
    }
}
