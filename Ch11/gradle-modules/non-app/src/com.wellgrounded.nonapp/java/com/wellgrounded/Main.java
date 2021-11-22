package com.wellgrounded.nonapp;

import com.wellgrounded.modlib.visible.UseThis;
import com.wellgrounded.modlib.hidden.CantTouchThis;

public class Main {
  public static void main(String[] args) {
    System.out.println(UseThis.getMessage());
    System.out.println(CantTouchThis.getMessage());
  }
}
