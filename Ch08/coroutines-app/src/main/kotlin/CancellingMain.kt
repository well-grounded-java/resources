package com.wellgrounded.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
  // Capture the coroutine object returned by `launch`
  val co = GlobalScope.launch {
    // Inside of coroutines we can call `delay` to wait a period of time
    delay(1000)
    println("Inside!")
  }
  // Cancel the coroutine immediately
  co.cancel()

  println("Outside and we cancelled Inside")

  // Wait as long as you like here, you'll never see the coroutine output
  Thread.sleep(2000)
}

