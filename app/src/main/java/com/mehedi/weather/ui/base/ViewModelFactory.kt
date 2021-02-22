package com.mehedi.weather.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mehedi.weather.data.api.ApiHelper
import com.mehedi.weather.data.repository.MainRepository
import com.mehedi.weather.ui.main.viewModel.WeatherViewModel

/**
 * Created by mhasan2 on 2/20/21.
 */
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                MainRepository(
                    apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

