package com.wellgrounded;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Test;
//import org.hamcrest.text.*;

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
