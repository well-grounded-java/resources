package ch15;

public class Main {
    public static void main(String[] args) {
        var cl = Main.class.getClassLoader();
        System.out.println(cl);
        System.out.println(cl.getParent());
        System.out.println(cl.getParent().getParent());
    }
}
