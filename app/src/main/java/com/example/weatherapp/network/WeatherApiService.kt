package com.example.weatherapp.network

import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.model.GeocodingResponseItem
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial"
    ): CityWeather

    @GET("geo/1.0/direct")
    suspend fun getLatLonByCity(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String
    ): List<GeocodingResponseItem>
}