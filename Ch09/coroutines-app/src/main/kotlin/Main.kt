package com.wellgrounded.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
       println("Inside!")
    }
    println("Outside")

    // If we exclude this sleep, most often you'll only see Outside as the
    // coroutine doesn't stop the app from exiting when done!
    Thread.sleep(1000)
}
