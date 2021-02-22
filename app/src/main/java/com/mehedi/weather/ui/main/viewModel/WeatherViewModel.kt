package com.mehedi.weather.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mehedi.weather.data.repository.MainRepository
import com.mehedi.weather.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by mhasan2 on 2/20/21.
 */
class WeatherViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getWeathers(city: String, apiKey: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getWeathers(city, apiKey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}