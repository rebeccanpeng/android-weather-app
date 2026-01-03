package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.CityData

class CityAdapter (private var cities: List<CityData>): RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.city_name)
        val countryName: TextView = itemView.findViewById(R.id.country)
        val currTemp: TextView = itemView.findViewById(R.id.curr_temp)
        val tempRange: TextView = itemView.findViewById(R.id.temp_range)
        val humidity: TextView = itemView.findViewById(R.id.humidity)
    }

    fun updateData(newCitiesData: List<CityData>) {
        val diffCallback = CityDiffCallback(cities, newCitiesData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        cities = newCitiesData
        diffResult.dispatchUpdatesTo(this)

    }

    class CityDiffCallback(
        private val oldList: List<CityData>,
        private val newList: List<CityData>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].cityName == newList[newItemPosition].cityName
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    fun onCityClicked(city: CityData, view: View) {
        val action = CityPickerFragmentDirections.actionCityPickerFragmentToCityDetailFragment(city)
        findNavController(view).navigate(action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val cityView = inflater.inflate(R.layout.city_item, parent, false)
        return ViewHolder(cityView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.cityName.text = city.cityName
        holder.countryName.text = city.country
        holder.currTemp.text = "${city.currTemp.toInt()}\u2109"
        holder.tempRange.text = "${city.tempMin.toInt()}\u00B0/${city.tempMax.toInt()}\u2109"
        holder.humidity.text = "${city.humidity}%"

        holder.itemView.setOnClickListener {
            onCityClicked(city, it)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

}