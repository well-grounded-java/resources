package ch04;

public class StringStack {
    private String[] values = new String[16];
    private int current = 0;

    public boolean push(String s) {
        if (current < values.length) {
            values[current] = s;
            current = current + 1;
        }
        return false;
    }

    public String pop() {
        if (current < 1) {
            return null;
        }
        current = current - 1;
        return values[current];
    }
}
