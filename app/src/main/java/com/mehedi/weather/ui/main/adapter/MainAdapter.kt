package com.mehedi.weather.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehedi.weather.R
import com.mehedi.weather.`interface`.OnItemClickListener
import com.mehedi.weather.data.model.WeatherDetails
import com.mehedi.weather.ui.main.adapter.MainAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by mhasan2 on 2/20/21.
 */
class MainAdapter(
    private val weatherDetailsList: ArrayList<WeatherDetails>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherDetails: WeatherDetails) {
            itemView.apply {
                textViewCondition.text = weatherDetails.weather.getOrNull(0)?.main
                textViewTemperature.text =
                    resources.getString(R.string.temp) + " " + weatherDetails.main.temp.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = weatherDetailsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(weatherDetailsList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(weatherDetailsList[position])
        }
    }

    fun addWeathers(weatherDetails: List<WeatherDetails>) {
        this.weatherDetailsList.apply {
            clear()
            addAll(weatherDetails)
        }

    }
}