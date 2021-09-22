package com.wellgrounded.kotlin

class MessageFromKotlin {
  companion object {
    @JvmStatic
    fun getMessage() : String {
      return "Howdy from Kotlin!"
    }
  }
}
