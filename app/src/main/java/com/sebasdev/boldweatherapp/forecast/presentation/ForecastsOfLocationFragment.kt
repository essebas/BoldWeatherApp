package com.sebasdev.boldweatherapp.forecast.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.sebasdev.boldweatherapp.R
import com.sebasdev.boldweatherapp.databinding.FragmentForecastsOfLocationBinding
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastDayModel
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.forecast.presentation.adapter.ForecastDayAdapter
import com.sebasdev.boldweatherapp.forecast.presentation.state.ForecastDetailsState
import com.sebasdev.boldweatherapp.search.presentation.state.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastsOfLocationFragment : Fragment() {

    private lateinit var binding: FragmentForecastsOfLocationBinding

    private val viewModel: ForecastsOfLocationViewModel by viewModels()
    private val adapter by lazy { ForecastDayAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastsOfLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idLocation = arguments?.getInt("idLocation")

        initViews()
        observeState()

        idLocation?.let {
            viewModel.forecastDetailsOfLocation(it.toString(), "3")
        }
    }

    private fun observeState() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.forecastDetailsState.collect { state ->
                when (state) {
                    is ForecastDetailsState.Loading -> handleProgressIndicator(true)
                    is ForecastDetailsState.Success -> renderForecastDetailsData(state.data)
                    is ForecastDetailsState.EmptyResult -> showScreenState(
                        R.drawable.empty_result_icon,
                        R.string.screen_status_no_results
                    )

                    is ForecastDetailsState.NoInternetConnection -> showScreenState(
                        R.drawable.cloud_off,
                        R.string.screen_status_no_internet_connection
                    )

                    is ForecastDetailsState.Error -> showScreenState(
                        R.drawable.error_general_icon,
                        R.string.screen_status_no_internet_connection
                    )

                    else -> Unit
                }
            }
        }
    }

    private fun renderForecastDetailsData(forecastDetails: ForecastModel) {
        Glide.with(requireContext())
            .load(forecastDetails.current.condition.icon)
            .centerCrop()
            .into(binding.cardCurrentForecastIcon)
        binding.apply {
            txvGeneralTitle.text = forecastDetails.location.name
            txvGeneralSubtitle.text = forecastDetails.location.country
            txvCurrentForecastCenterTitle.text = getString(
                R.string.celsius_degrees_text,
                forecastDetails.current.tempInCelsius.toString()
            )
            txvCurrentForecastCenterSubtitle.text = forecastDetails.current.condition.text
            txvForecastTextHeat.text = getString(
                R.string.celsius_degrees_text,
                forecastDetails.current.heatIndexInCelsius.toString()
            )
            txvForecastTextWind.text = getString(
                R.string.speed_in_kilometers_hour_text,
                forecastDetails.current.windSpeedInKilometers.toString()
            )
            txvForecastTextHumidity.text = "${forecastDetails.current.humidity}%"
            txvCurrentForecastLeftRight.text = forecastDetails.current.lastUpdated
        }
        showForecastByDay(forecastDetails.forecast)
        binding.cardCurrentForecast.visibility = View.VISIBLE
        binding.txvForecastDaysLabel.visibility = View.VISIBLE
        handleProgressIndicator(false)

    }

    private fun showForecastByDay(forecastList: List<ForecastDayModel>) {
        adapter.injectData(forecastList)
    }

    private fun initViews() {
        binding.recyclerForecastDays.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = this@ForecastsOfLocationFragment.adapter
        }
    }

    private fun handleProgressIndicator(isLoading: Boolean) {
        binding.circularProgress.apply {
            isIndeterminate = isLoading
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showScreenState(
        @DrawableRes statusIcon: Int,
        @StringRes stateText: Int
    ) {
        handleProgressIndicator(false)
        binding.statusScreen.apply {
            imvStatusIcon.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    statusIcon
                )
            )
            txvStatusText.text = getText(stateText)
            root.visibility = View.VISIBLE
        }
    }
}