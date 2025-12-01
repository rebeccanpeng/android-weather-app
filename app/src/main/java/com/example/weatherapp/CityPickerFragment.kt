package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class CityPickerFragment : Fragment(R.layout.city_picker) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: CityPickerViewModel by viewModels()
        viewLifecycleOwner.lifecycleScope.launch {
            // 1. Coroutine launched here lives until Fragment.onDestroy()

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 2. This block runs only between STARTED and STOPPED
                viewModel.uiState.collect {
                    // Update UI safely
                }
            }
            // 3. This line won't execute until the Fragment is DESTROYED
        }
    }
}