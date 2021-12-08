package ch16.fx.sealed;

public abstract sealed class Pet {
    private final String name;

    protected Pet(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public static final class Cat extends Pet {
        public Cat(String name) {
            super(name);
        }

        void meow() {
            System.out.println(name() +" meows");
        }
    }

    public static final class Dog extends Pet {
        public Dog(String name) {
            super(name);
        }

        void bark() {
            System.out.println(name() +" barks");
        }
    }
}
