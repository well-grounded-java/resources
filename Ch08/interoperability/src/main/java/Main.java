package com.wellgrounded.kotlin;

import com.wellgrounded.kotlin.MainKt;
import com.wellgrounded.kotlin.Person;

public class Main {
  public static void main(String[] args) {
    // Calling a top-level function in Kotlin
    MainKt.shout();

    // Using a Kotlin-defined class
    Person p = new Person("Some Body");
    System.out.println(p.getName());

    p.setName("Somebody Else");
    System.out.println(p.getName());

    // Kotlin's default values
    p.greet();
    p.greet("Howdy");
  }
}
