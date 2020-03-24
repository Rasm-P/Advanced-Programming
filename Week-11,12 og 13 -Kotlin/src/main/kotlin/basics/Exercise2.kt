package basics

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun run_threads(max: Int) {
    for (index in 0..max) {
        thread {
            sleep(100)
            if (index%(max/10) == 0) println("running thread $index")
        }
    }
}

fun run_coroutines(max: Int) = runBlocking {
    for (index in 0..max) {
        launch {
            delay(100)
            if (index%(max/10) == 0) println("running coroutine $index")
        }
    }
}

fun main() {
    val count = 1_000_000
    val mc = measureTimeMillis {
        run_coroutines(count)
    }
    println("Coroutines: $mc")
    val mt = measureTimeMillis {
        run_threads(count)
    }
    println("Threads: $mt")
}