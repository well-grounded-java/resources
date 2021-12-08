package ch05;

public class BadThread {
    public static void main(String[] args) {
        var badThread = new Thread(() -> {
            throw new UnsupportedOperationException();
        });

        // Set a name before starting the thread
        badThread.setName("An Exceptional Thread");

        // Set the handler
        badThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.printf("Thread %d '%s' has thrown exception " +
                            "%s at line %d of %s",
                    t.getId(),
                    t.getName(),
                    e.toString(),
                    e.getStackTrace()[0].getLineNumber(),
                    e.getStackTrace()[0].getFileName()); });

        badThread.start();
    }
}
