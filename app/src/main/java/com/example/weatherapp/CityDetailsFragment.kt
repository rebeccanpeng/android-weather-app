package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.CityDetailBinding
import com.example.weatherapp.model.CityData
import kotlinx.coroutines.launch


class CityDetailsFragment: Fragment(R.layout.city_detail) {
    private val args: CityDetailsFragmentArgs by navArgs()
    private val viewModel: CityDetailsViewModel by viewModels()
    private var _binding: CityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initializeCity(args.city)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect { city ->
                    city?.let {
                        updateUi(city)
                    }
                }
            }
        }
    }

    private fun updateUi(city: CityData) {
        binding.cityTitleTextView.text = city.cityName
    }
}