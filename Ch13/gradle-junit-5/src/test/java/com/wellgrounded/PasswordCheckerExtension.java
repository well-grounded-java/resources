package com.wellgrounded;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class PasswordCheckerExtension implements AfterEachCallback, BeforeEachCallback {
    private PasswordChecker checker;

    PasswordCheckerExtension(PasswordChecker checker) {
        this.checker = checker;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        checker.reset();
        checker.start();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        checker.stop();
    }
}
