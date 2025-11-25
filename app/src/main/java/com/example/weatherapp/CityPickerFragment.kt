package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CityPickerFragment : Fragment(R.layout.city_picker) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: CityPickerViewModel by viewModels()
        lifecycleScope.launch {
            viewModel.uiState.collect {
                // Update UI
            }
        }
    }
}