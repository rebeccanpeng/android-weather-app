package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityData(
    val cityName: String,
    val country: String,
    val currTemp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int
) : Parcelable
