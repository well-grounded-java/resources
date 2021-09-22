package com.wellgrounded

class MessageFromKotlin {
  companion object {
    @JvmStatic
    fun getMessage() : String {
      return "Howdy from Kotlin!"
    }
  }
}
