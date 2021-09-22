package com.wellgrounded;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class MainTest {
  @Test
  public void checkMessage() {
    var unused = "";
    assertNotNull(Main.getMessage());
  }

  public boolean equals(Object anObject) {
    return true;
  }
}
