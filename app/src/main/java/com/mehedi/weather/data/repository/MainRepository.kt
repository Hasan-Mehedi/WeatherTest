package com.mehedi.weather.data.repository

import com.mehedi.weather.data.api.ApiHelper

/**
 * Created by mhasan2 on 2/20/21.
 */
class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeathers(city: String, apiKey: String) = apiHelper.getWeathers(city, apiKey)
}