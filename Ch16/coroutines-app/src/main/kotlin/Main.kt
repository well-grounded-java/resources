package com.wgjd

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.CoroutineContext

fun main() {
  GlobalScope.launch {
    where()
    delay(1000)
    where()
    sus()
    where()

    var result: Deferred<Int> = async {
      delay(1000)
      where()
      10;
    }

    println(result.await())
  }

  val context: CoroutineContext = newFixedThreadPoolContext(3, "In-The-Pool")
    .plus(CoroutineExceptionHandler { _, thrown -> println(thrown.message + "!!!!") })
    .plus(CoroutineName("Thready"))

  CoroutineScope(context).launch {
    where()
    delay(100)
    where()
    delay(100)
    where()
    throw RuntimeException("yikes")
  }

  val supervised = GlobalScope.launch {
    supervisorScope {
      launch { throw RuntimeException("Failing...") }
    }
  }

  val failed = GlobalScope.launch {
    launch { throw RuntimeException("Failing...") }
  }

  Thread.sleep(2000)

  println("Cancelled ${failed.isCancelled}")
  println("Cancelled ${supervised.isCancelled}")
}

fun where() {
  println("On thread ${Thread.currentThread().name}")
}

suspend fun sus() {
  println("Totally sus...")
}