package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class CityPickerFragment : Fragment(R.layout.city_picker) {
    val viewModel: CityPickerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCities)
        val adapter = CityAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            // 1. Coroutine launched here lives until Fragment.onDestroy()

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 2. This block runs only between STARTED and STOPPED
                viewModel.uiState.collect {
                    // Update UI safely
                    adapter.updateData(it)
                }
            }
            // 3. This line won't execute until the Fragment is DESTROYED
        }
    }
}