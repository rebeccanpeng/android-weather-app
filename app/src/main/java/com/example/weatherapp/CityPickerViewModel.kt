package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityPickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<List<String>>(emptyList())
    val uiState: StateFlow<List<String>> = _uiState

    private val cities = listOf(
        "McLean",
        "Los Angeles",
        "New York City",
        "Chicago",
        "Houston",
        "Seattle",
        "Honolulu",
        "Denver",
        "San Francisco",
        "Dallas",
        "Portland",
        "Detroit",
        "New Orleans"
    )
    private val apiKey = BuildConfig.WEATHER_API_KEY

    init {
        getCityData()
    }

    private fun getCityData() {
        viewModelScope.launch {
            try {
                val deferredCityData = cities.map { cityName ->
                    async { getWeatherForCity(cityName) }
                }
                val results = deferredCityData.awaitAll().filterNotNull()
                _uiState.value = results


            } catch (e: Exception) {
                Log.e("WeatherApp", "Geocoding or Weather call failed", e)
            }
        }
    }

    private suspend fun getWeatherForCity(cityName: String): String? {
        return try {
            val geocodingResponse = RetrofitClient.weatherApiService.getLatLonByCity(cityName, apiKey = apiKey)
            if (geocodingResponse.isEmpty()) return null
            val location = geocodingResponse[0]
            val weather = RetrofitClient.weatherApiService.getWeather(location.lat, location.lon, apiKey)
            "${weather.name}: ${weather.main.temp}°F"
        } catch (e: Exception) {
            Log.e("WeatherApp", "Geocoding or Weather call failed for $cityName", e)
            null
        }
    }
}