package com.example.weatherapp.model

import com.squareup.moshi.Json

data class MainData(
    val temp: Double,
    val humidity: Int,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double
)
