package com.mehedi.weather.data.model

/**
 * Created by mhasan2 on 2/20/21.
 */
data class Weather(
    val list: List<WeatherDetails>
)

data class WeatherDetails(
    val main: MainObject,
    val weather: List<WeatherObject>
)

data class MainObject(
    val temp: Float,
    val feels_like: Float
)

data class WeatherObject(
    val main: String,
    val description: String
)