package basics

import kotlinx.coroutines.*

fun main_exampled_1() {
    GlobalScope.launch {
        delay(1000)
        println(" World!")
    }
    print("Hello")
    // thread { sleep(2000) }
    runBlocking { delay(2000) }
}

fun main_example_2() = runBlocking {
    launch {
        delay(1000)
        println(" World!")
    }
    print("Hello")
}

fun main_example_3() {
    val result: Deferred<String> = GlobalScope.async {
        delay(1000)
        "World"
    }
    runBlocking {
        val who = result.await()
        println("Hello $who")
    }
}

fun main_example_4() = runBlocking {
    val result = async { delay(1000); "World!" }
    println("Hello ${result.await()}")
}