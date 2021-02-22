package com.mehedi.weather.data.api

import com.mehedi.weather.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mhasan2 on 2/20/21.
 */
interface ApiService {

    @GET("data/2.5/forecast")
    suspend fun getWeathers(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Weather

}