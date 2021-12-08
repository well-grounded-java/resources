package ch06;

import java.util.concurrent.atomic.AtomicBoolean;

public class TaskManager implements Runnable {
    private final AtomicBoolean shutdown = new AtomicBoolean(false);

    public void shutdown() {
        shutdown.set(true);
    }

    @Override
    public void run() {
        while (!shutdown.get()) {
            // do some work - e.g. process a work unit
        }
    }
}