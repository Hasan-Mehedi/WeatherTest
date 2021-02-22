package com.mehedi.weather.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mehedi.weather.R
import kotlinx.android.synthetic.main.fragment_lookup.*

/**
 * Created by mhasan2 on 2/20/21.
 */
class LookupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lookup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLookup.setOnClickListener {
            if (editTextCityName.text.toString().isNotBlank()) {
                getWeatherFragment(editTextCityName.text.toString())
            }
        }
    }

    private fun getWeatherFragment(cityName: String) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(
            R.id.fragment_container,
            WeatherFragment.newInstance(
                cityName
            )
        )
            ?.addToBackStack(null)?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LookupFragment()
    }
}