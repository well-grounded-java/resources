package com.wellgrounded;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.*;

public class PasswordCheckerTest {
    private static PasswordChecker checker = new PasswordChecker();

    @RegisterExtension
    static PasswordCheckerExtension ext = new PasswordCheckerExtension(checker);

    @BeforeEach
    public void setup() {
        checker.reset();
    }

    @Test
    public void ok() {
        assertTrue(checker.isOk("abcd1234!"));
        assertEquals(1L, checker.getStat(true));
        assertEquals(0L, checker.getStat(false));
    }

    @Test
    public void tooShort() {
        assertFalse(checker.isOk("abcd"));
        assertEquals(0L, checker.getStat(true));
        assertEquals(1L, checker.getStat(false));
    }

    @Test
    public void nullThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            checker.isOk(null);
        });
    }
}