package com.mehedi.weather.data.api

/**
 * Created by mhasan2 on 2/20/21.
 */
class ApiHelper(private val apiService: ApiService) {

    suspend fun getWeathers(city: String, apiKey: String) = apiService.getWeathers(city, apiKey)
}