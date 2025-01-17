package com.example.showcaseapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val numberFlow: Flow<Int> = flow {
            for (i in 1..100) {
                delay(100)
                emit(i)
                println("Emitted: $i")
            }
        }

        // TODO: Zmodyfikuj kod w taki sposób, żeby przetworzyć tylko 10 pierwszych elementów
        //  Dodatkowo, wypisz tylko najnowsze wartości wyemitowane przez flow
        //  Poszukaj operatorów w dokumentacji: https://kotlinlang.org/docs/flow.html
        numberFlow
            .take(10)
            .collectLatest { value ->
                println("Collected: $value")
                delay(300)
            }
    }
    println("Execution took: $time")
}
