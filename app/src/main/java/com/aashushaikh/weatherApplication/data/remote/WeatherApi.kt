package com.aashushaikh.weatherApplication.data.remote

import com.aashushaikh.weatherApplication.BuildConfig
import com.aashushaikh.weatherApplication.data.WeatherResponse
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") city: String
    ): Response<WeatherResponse>

}