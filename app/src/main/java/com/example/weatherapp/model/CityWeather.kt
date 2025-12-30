package com.example.weatherapp.model

data class CityWeather(
    val id: Int,
    val name: String,
    val main: MainData,
    val weather: List<WeatherDescription>
)
