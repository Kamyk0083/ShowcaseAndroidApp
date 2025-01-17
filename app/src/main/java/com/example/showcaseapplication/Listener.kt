package com.example.showcaseapplication

interface Listener {
    fun onEvent(event: String)
}

object ListenerManager {
    private val listeners = mutableSetOf<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun emitEvent(event: String) {
        listeners.forEach { it.onEvent(event) }
    }
}