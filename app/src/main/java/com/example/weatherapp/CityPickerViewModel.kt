package com.example.weatherapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CityPickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<List<Int>>((1..100).toList())
    val uiState: StateFlow<List<Int>> = _uiState

}