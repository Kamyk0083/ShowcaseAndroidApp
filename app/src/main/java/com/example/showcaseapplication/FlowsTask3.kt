package com.example.showcaseapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val numberFlow = flow {
        for (i in 1..5) {
            emit(i)
            delay(200)
        }
    }


    val stringFlow = flow {
        val letters = listOf("A", "B", "C", "D", "E")
        for (letter in letters) {
            emit(letter)
            delay(400)
        }
    }
    val startTime = System.currentTimeMillis()

    numberFlow.conflate().combine(stringFlow){a, b -> "$a -> $b"}
        .collect(){
            value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
    // TODO: Wypisz połączone liczby i litery z flow wyżej. Połącz najświeższe wyemitowane wartości
    //  Poszukaj jak to zrobić w dokumentacji: https://kotlinlang.org/docs/flow.html
}
