package com.wellgrounded;

import java.util.HashMap;
import java.util.Map;

public class PasswordChecker {
    private Map<Boolean, Long> stats = new HashMap<>();
    private boolean started = false;

    public void start() {
        // Imagine it had state to set up -- reading files, etc.
        started = true;
    }

    public void stop() {
        // Clean up
        started = false;
    }

    public void reset() {
        stats.clear();
    }

    public boolean isOk(String password) {
        if (!started) {
            throw new IllegalStateException("Server not started");
        }

        if (password == null) {
            throw new IllegalArgumentException("password must be non-null");
        }

        return password.length() > 8;
    }
}
