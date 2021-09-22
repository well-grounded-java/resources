package com.wellgrounded;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;

import static org.junit.Assert.*;

public class PasswordCheckerTest {
    private PasswordChecker checker = new PasswordChecker();

    @Rule
    public ExternalResource passwordServer = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            super.before();
            checker.reset();
            checker.start();
        }

        @Override
        protected void after() {
            super.after();
            checker.stop();
        }
    };

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void ok() {
        assertTrue(checker.isOk("abcd1234!"));
    }

    @Test
    public void tooShort() {
        assertFalse(checker.isOk("abcd"));
    }

    @Test
    public void nullThrows() {
        exceptions.expect(IllegalArgumentException.class);
        checker.isOk(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void alsoThrows() {
        checker.isOk(null);
    }
}