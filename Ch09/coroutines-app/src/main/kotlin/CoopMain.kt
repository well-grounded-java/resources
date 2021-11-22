package com.wellgrounded.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // Start our parent coroutine as before
    val co = GlobalScope.launch {
      // Start two child coroutines. `coroutineScope` associates those to the
      // enclosing scope - in this case our global coroutine.
       coroutineScope {
          delay(1000)
          println("First")
       }
       coroutineScope {
          delay(1000)
          println("Second")
       }
    }

    // Cancel the parent coroutine
    co.cancel()

    // Again, we can wait here but we won't see any output
    println("Only this output because all the children cancelled too")
    Thread.sleep(4000)
}
