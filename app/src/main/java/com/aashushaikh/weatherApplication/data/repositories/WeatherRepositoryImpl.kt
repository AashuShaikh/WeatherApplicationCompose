package com.aashushaikh.weatherApplication.data.repositories

import com.aashushaikh.weatherApplication.data.WeatherResponse
import com.aashushaikh.weatherApplication.data.remote.WeatherApi
import com.aashushaikh.weatherApplication.domain.repositories.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(city: String): Response<WeatherResponse> {
        return weatherApi.getWeather(city = city)
    }
}