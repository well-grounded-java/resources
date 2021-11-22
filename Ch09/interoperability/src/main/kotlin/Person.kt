package com.wellgrounded.kotlin

class Person(var name: String) {
  // Annotating with JvmOverloads allows Java to use our default values too
  @JvmOverloads
  fun greet(words: String = "Hi there") {
    println(words)
  }
}
