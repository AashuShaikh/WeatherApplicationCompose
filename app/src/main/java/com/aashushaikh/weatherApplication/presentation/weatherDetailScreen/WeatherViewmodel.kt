package com.aashushaikh.weatherApplication.presentation.weatherDetailScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aashushaikh.weatherApplication.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewmodel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _state = mutableStateOf(WeatherDetailState())
    val state: State<WeatherDetailState> = _state

    fun getWeather(city: String){
        _state.value = WeatherDetailState(isLoading = true)
        Log.d("hhh", "getWeather: $city")
        viewModelScope.launch{
            val response = repository.getWeather(city = city)
            Log.d("hhh", "getWeather1: $response")
            if(response.isSuccessful){
                _state.value = WeatherDetailState(weather = response.body())
                Log.d("hhh", "getWeather2: ${response.body()}")
            } else {
                _state.value = WeatherDetailState(error = response.message())
                Log.d("hhh", "getWeather3: ${response.message()}")
            }
        }
    }

}