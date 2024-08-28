package com.aashushaikh.weatherApplication.di

import com.aashushaikh.weatherApplication.data.remote.WeatherApi
import com.aashushaikh.weatherApplication.data.repositories.WeatherRepositoryImpl
import com.aashushaikh.weatherApplication.domain.repositories.WeatherRepository
import com.aashushaikh.weatherApplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

}