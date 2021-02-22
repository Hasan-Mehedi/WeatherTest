package com.mehedi.weather.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mehedi.weather.R
import com.mehedi.weather.data.model.WeatherDetails
import com.mehedi.weather.ui.main.viewModel.WeatherDetailsViewModel
import kotlinx.android.synthetic.main.fragment_weather_details.*

/**
 * Created by mhasan2 on 2/20/21.
 */
class WeatherDetailsFragment : Fragment() {
    private var viewModel: WeatherDetailsViewModel? = null
    private var cityName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupView()
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = cityName
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        viewModel?.weatherDetailsLiveData?.observe(viewLifecycleOwner, Observer { weatherDetails ->
            temperature.text = weatherDetails.main.temp.toString()
            feelsLike.text =
                resources.getString(R.string.feels_like) + " " + weatherDetails.main.feels_like.toString()
            weather.text = weatherDetails.weather.getOrNull(0)?.main
            description.text = weatherDetails.weather.getOrNull(0)?.description
        })

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            (activity as? AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            activity?.onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (isRemoving) {
            (activity as? AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(weatherDetails: WeatherDetails, cityName: String): WeatherDetailsFragment {
            val fragment =
                WeatherDetailsFragment()
            fragment.viewModel =
                WeatherDetailsViewModel(
                    weatherDetails
                )
            fragment.cityName = cityName
            return fragment
        }
    }
}