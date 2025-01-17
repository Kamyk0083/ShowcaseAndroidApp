package com.example.showcaseapplication

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    getInputNumbersFlow()
        .map { number ->
            getSquare(number)
        }
        .collect { square ->
            println("Squared value: $square")
        }
}

private fun getInputNumbersFlow(): Flow<Int> = flow {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    for (number in numbers) {
        emit(number)
    }
}

private fun getSquare(number: Int): Int = number * number
