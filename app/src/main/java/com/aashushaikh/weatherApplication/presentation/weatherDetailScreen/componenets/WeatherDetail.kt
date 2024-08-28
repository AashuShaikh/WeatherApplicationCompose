@file:OptIn(ExperimentalGlideComposeApi::class)

package com.aashushaikh.weatherApplication.presentation.weatherDetailScreen.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aashushaikh.weatherApplication.R
import com.aashushaikh.weatherApplication.presentation.weatherDetailScreen.WeatherDetailState
import com.aashushaikh.weatherApplication.presentation.weatherDetailScreen.WeatherViewmodel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun WeatherScreen(viewmodel: WeatherViewmodel, modifier: Modifier = Modifier) {
    val weatherDetailState = viewmodel.state.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        WeatherCityInput {city ->
            viewmodel.getWeather(city = city)
        }
        WeatherDetailResult(weatherDetailState = weatherDetailState)
    }
}

@Composable
fun WeatherCityInput(onBtnSearchClicked: (String) -> Unit) {
    val city = rememberSaveable {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = city.value,
            onValueChange = {
                city.value = it
            },
            label = {
                Text(text = "Enter City Name")
            },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
            onClick = {
                onBtnSearchClicked(city.value)
            },
            enabled = city.value.isNotEmpty(),
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = "Search")
        }
    }
}

@Composable
fun WeatherDetailResult(weatherDetailState: WeatherDetailState) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            when{
                weatherDetailState.isLoading -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Loading...",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                weatherDetailState.weather != null -> {
                    WeatherResult(weatherDetailState = weatherDetailState)
                }
                weatherDetailState.error != null -> {
                    Row{
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Error: ${weatherDetailState.error}")
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherResult(weatherDetailState: WeatherDetailState) {
    GlideImage(
        model = "https:${weatherDetailState.weather?.current?.condition?.icon}",
        contentDescription = "",
        modifier = Modifier
            .width(250.dp)
            .height(250.dp),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "${weatherDetailState.weather?.current?.condition?.text},",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${weatherDetailState.weather?.current?.temp_c}°C",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = ""
        )
        Text(
            text = "${weatherDetailState.weather?.location?.name}, ${weatherDetailState.weather?.location?.country}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}