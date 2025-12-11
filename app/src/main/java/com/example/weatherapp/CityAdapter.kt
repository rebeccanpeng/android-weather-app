package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter (private var cities: List<Int>): RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.city_name)
    }

    fun updateData(newCitiesData: List<Int>) {
        cities = newCitiesData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val cityView = inflater.inflate(R.layout.city_item, parent, false)
        return ViewHolder(cityView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city: Int = cities[position]
        val textView = holder.cityName
        val context = holder.itemView.context
        textView.text = context.getString(R.string.list_item_format, city)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

}