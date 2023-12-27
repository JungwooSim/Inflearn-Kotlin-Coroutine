package lec03

import kotlin.time.measureTime
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import printWithThread

fun main() {
  example5()
}


fun example5() = runBlocking {
  val time = measureTime {
    val job1 = async { apiCall1() }
    val job2 = async { apiCall2() }
    printWithThread(job1.await() + job2.await())
  }

  printWithThread("소요시간 : ${time} ms")
}
suspend fun apiCall1(): Int {
  delay(1000)
  return 1
}

suspend fun apiCall2(): Int {
  delay(1000)
  return 2
}

fun example4() = runBlocking {
  val job1 = launch {
    delay(1000)
    printWithThread("Job 1")
  }
  job1.join()

  val job2 = launch {
    delay(1000)
    printWithThread("Job 2")
  }
}

fun example3() = runBlocking {
  val job = launch {
    (1..5).forEach {
      printWithThread(it)
      delay(500)
    }
  }

  delay(1000)
  job.cancel()
}

fun example2() = runBlocking {
  val job = launch(start = CoroutineStart.LAZY) {
    printWithThread("Hello launch")
  }

  delay(1000)
  job.start()
}
fun example1() {
  runBlocking {
    printWithThread("START")

    launch {
      delay(2000)
      printWithThread("LAUNCH END")
    }
  }

  printWithThread("END")
}