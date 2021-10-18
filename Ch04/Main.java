public class Main {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("MyClass");
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
