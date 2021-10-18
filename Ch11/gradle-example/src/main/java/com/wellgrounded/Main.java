package com.wellgrounded;

import com.wellgrounded.kotlin.MessageFromKotlin;

public class Main {
  public static String getMessage() {
    return "Gradle for fun and profit";
  }

  public static void main(String[] args) {
    System.out.println(MessageFromKotlin.getMessage());
    System.out.println(getMessage());
  }
}
