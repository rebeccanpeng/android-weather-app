package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
