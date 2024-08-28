package com.aashushaikh.weatherApplication.presentation.weatherDetailScreen

import com.aashushaikh.weatherApplication.data.WeatherResponse

data class WeatherDetailState (
    val isLoading: Boolean = false,
    val weather: WeatherResponse? = null,
    val error: String? = null
)