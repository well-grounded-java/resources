package com.wellgrounded;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class LongRunningIT {
  @Test
  public void checkMessage() {
    var unused = "";
    assertNotNull(Main.getMessage());
  }
}
