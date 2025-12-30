package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.CityDetailBinding

class CityDetailFragment: Fragment(R.layout.city_detail) {
    private val args:CityDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CityDetailBinding.bind(view)
        binding.cityTitleTextView.text = args.city
    }
}