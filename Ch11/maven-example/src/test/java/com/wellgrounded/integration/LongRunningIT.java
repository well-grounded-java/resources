package com.wellgrounded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LongRunningIT {
  @Test
  public void checkMessage() {
    var unused = "";
    assertNotNull(Main.getMessage());
  }
}
