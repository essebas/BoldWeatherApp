package com.sebasdev.boldweatherapp.forecast.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sebasdev.boldweatherapp.R
import com.sebasdev.boldweatherapp.databinding.FragmentForecastsOfLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastsOfLocationFragment : Fragment() {

    private lateinit var binding: FragmentForecastsOfLocationBinding

    private val viewModel: ForecastsOfLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastsOfLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState().observe(viewLifecycleOwner, Observer { forecastModel ->
            binding.txvResult.text = forecastModel.toString()
        })

        viewModel.forecastDetailsOfLocation()
    }
}