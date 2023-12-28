package lec05

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import printWithThread

fun main() = runBlocking {
  val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    printWithThread("예외")
  }

  val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
    throw IllegalArgumentException()
  }

  delay(1_000)
}