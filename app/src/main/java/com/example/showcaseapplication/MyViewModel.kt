package com.example.showcaseapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow("Initial State")
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        _uiState.update { "Updated State 1" }
    }

    fun simulateStateChange() {
        _uiState.update { "Updated State 2" }
    }
}