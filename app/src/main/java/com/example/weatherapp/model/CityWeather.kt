package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityWeather(
    val id: Int,
    val name: String,
    val main: MainData,
    val weather: List<WeatherDescription>
) : Parcelable
