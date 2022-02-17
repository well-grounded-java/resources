package com.wellgrounded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {
  @Test
  public void checkMessage() {
    var unused = "";
    assertNotNull(Main.getMessage());
  }

  // Uncomment to cause a SpotBugs failure
  //public boolean equals(Object anObject) {
    //return true;
  //}
}
