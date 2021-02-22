package com.mehedi.weather.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehedi.weather.R
import com.mehedi.weather.`interface`.OnItemClickListener
import com.mehedi.weather.data.api.ApiHelper
import com.mehedi.weather.data.api.RetrofitBuilder
import com.mehedi.weather.data.model.WeatherDetails
import com.mehedi.weather.ui.base.ViewModelFactory
import com.mehedi.weather.ui.main.adapter.MainAdapter
import com.mehedi.weather.ui.main.viewModel.WeatherViewModel
import com.mehedi.weather.utils.Status
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by mhasan2 on 2/20/21.
 */
class WeatherFragment : Fragment(),
    OnItemClickListener {
    private var cityName: String = ""
    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: MainAdapter
    private val apiKey: String = "65d00499677e59496ca2f318eb68c049"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = cityName
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(
                    RetrofitBuilder.apiService
                )
            )
        ).get(WeatherViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(
            arrayListOf(),
            this
        )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getWeathers(cityName, apiKey).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("shaon", "success is called")
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { weathers ->
                            retrieveList(weathers.list)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(weatherDetails: List<WeatherDetails>) {
        adapter.apply {
            addWeathers(weatherDetails)
            notifyDataSetChanged()
        }
    }

    override fun onItemClick(weatherDetails: WeatherDetails) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(
            R.id.fragment_container,
            WeatherDetailsFragment.newInstance(
                weatherDetails,
                cityName
            )
        )
            ?.addToBackStack(null)?.commit()
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
        fun newInstance(cityName: String): WeatherFragment {
            val fragment =
                WeatherFragment()
            fragment.cityName = cityName
            return fragment
        }

    }

}