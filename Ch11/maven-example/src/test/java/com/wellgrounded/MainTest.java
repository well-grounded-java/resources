package com.wellgrounded;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {
  @Test
  public void checkMessage() {
    //var matcher = new StringContainsInOrder(List.of("boo"));
    //System.out.println("Hamcrest: " + matcher.getClass().getPackage().getImplementationVersion().toString());
    var unused = "";
    assertNotNull(Main.getMessage());
  }

  public boolean equals(Object anObject) {
    return true;
  }
}
