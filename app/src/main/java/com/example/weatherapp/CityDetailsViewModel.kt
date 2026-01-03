package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.CityData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CityDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CityData?>(null)
    val uiState: StateFlow<CityData?> = _uiState.asStateFlow()

    fun initializeCity(cityData: CityData) {
        _uiState.value = cityData
    }


}