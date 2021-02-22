package com.mehedi.weather.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehedi.weather.data.model.WeatherDetails

/**
 * Created by mhasan2 on 2/20/21.
 */
class WeatherDetailsViewModel(weatherDetails: WeatherDetails) : ViewModel() {
    var weatherDetailsLiveData = MutableLiveData<WeatherDetails>()

    init {
        weatherDetailsLiveData.value = weatherDetails
    }
}