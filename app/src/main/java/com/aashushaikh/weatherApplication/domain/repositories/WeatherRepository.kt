package com.aashushaikh.weatherApplication.domain.repositories

import com.aashushaikh.weatherApplication.data.WeatherResponse
import retrofit2.Response

interface WeatherRepository {

    suspend fun getWeather(city: String): Response<WeatherResponse>

}