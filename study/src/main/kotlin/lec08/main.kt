package lec08

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import printWithThread

fun main() = runBlocking {
  printWithThread("START")
  printWithThread(calculateResult())
  printWithThread("END")
}

suspend fun calculateResult(): Int = coroutineScope {
  val num1 = async {
    delay(1_000L)
    10
  }

  val num2 = async {
    delay(1_000L)
    10
  }

  num1.await() + num2.await()
}