package com.wellgrounded;

import java.util.HashMap;
import java.util.Map;

public class PasswordChecker {
    private static Map<Boolean, Long> stats = new HashMap<>();
    private boolean started;

    public void start() {
        // Imagine it had state to set up -- reading files, etc.
        started = true;
    }

    public void stop() {
        // Clean up
        started = false;
    }

    public long getStat(Boolean key) {
        return stats.getOrDefault(key, 0L);
    }

    public void reset() {
        stats.clear();
    }

    public boolean isOk(String password) {
        if (!started) {
            throw new IllegalStateException("Server not started");
        }

        if (password == null) {
            increment(false);
            throw new IllegalArgumentException("password must be non-null");
        }

        var result = password.length() > 8;
        increment(result);

        return result;
    }

    private void increment(Boolean key) {
        var count = stats.getOrDefault(key, 0L);
        stats.put(key, count + 1);
    }
}
