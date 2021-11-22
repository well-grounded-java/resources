package com.wellgrounded;

import com.wellgrounded.MessageFromKotlin;

public class Main {
  public static String getMessage() {
    return "Maven for fun and profit";
  }

  public static void main(String[] args) {
    System.out.println(MessageFromKotlin.getMessage());
    System.out.println(getMessage());
  }
}
