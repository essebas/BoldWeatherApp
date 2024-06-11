package com.sebasdev.boldweatherapp.search.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.BackEventCompat
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasdev.boldweatherapp.R
import com.sebasdev.boldweatherapp.databinding.FragmentSearchBinding
import com.sebasdev.boldweatherapp.databinding.ScreenStateBinding
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import com.sebasdev.boldweatherapp.search.presentation.adapter.LocationsAdapter
import com.sebasdev.boldweatherapp.search.presentation.state.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()
    private val adapter by lazy { LocationsAdapter(::onClickSearchedLocation) }
    private val adapterLocationsSaved by lazy {
        LocationsAdapter(::onClickSearchedLocation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeStates()

    }

    private fun initViews() {
        val itemDecorator = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )
        ContextCompat.getDrawable(
            requireContext(),
            androidx.constraintlayout.widget.R.drawable.abc_list_divider_material
        )
            ?.let {
                itemDecorator.setDrawable(
                    it
                )
            }
        binding.recyclerSearchedLocations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchFragment.adapter
            addItemDecoration(itemDecorator)
        }
        binding.recyclerLocationsSaved.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchFragment.adapterLocationsSaved
            addItemDecoration(itemDecorator)
        }
        binding.searchView.editText.addTextChangedListener {
            viewModel.searchLocation(it.toString())
        }
    }

    private fun observeStates() {
        observeSearchLocationsResult()
        observeLocationsSavedResult()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocationsSaved()
    }

    private fun observeLocationsSavedResult() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.searchLocationsSaved.collect { state ->
                when (state) {
                    is SearchState.Loading -> handleProgressIndicator(true)

                    is SearchState.Success -> showSearchedLocations(
                        state.data,
                        adapterLocationsSaved,
                        binding.statusScreenOnSavedLocations
                    )

                    is SearchState.EmptyResult -> showScreenState(
                        R.drawable.empty_recent_locations_icon,
                        R.string.screen_status_no_results_saved_locations,
                        binding.statusScreenOnSavedLocations,
                        adapterLocationsSaved
                    )

                    is SearchState.Error -> showScreenState(
                        R.drawable.error_general_icon,
                        R.string.screen_status_no_internet_connection,
                        binding.statusScreenOnSearch,
                        adapter
                    )

                    else -> Unit
                }
            }
        }
    }

    private fun observeSearchLocationsResult() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.searchState.collect { state ->
                when (state) {
                    is SearchState.Loading -> handleProgressIndicator(true)

                    is SearchState.Success -> showSearchedLocations(
                        state.data,
                        adapter,
                        binding.statusScreenOnSearch
                    )

                    is SearchState.EmptyResult -> showScreenState(
                        R.drawable.empty_result_icon,
                        R.string.screen_status_no_results,
                        binding.statusScreenOnSearch,
                        adapter
                    )

                    is SearchState.NoInternetConnection -> showScreenState(
                        R.drawable.cloud_off,
                        R.string.screen_status_no_internet_connection,
                        binding.statusScreenOnSearch,
                        adapter
                    )

                    is SearchState.Error -> showScreenState(
                        R.drawable.error_general_icon,
                        R.string.screen_status_no_internet_connection,
                        binding.statusScreenOnSearch,
                        adapter
                    )

                    else -> Unit
                }
            }
        }
    }

    private fun showScreenState(
        @DrawableRes statusIcon: Int,
        @StringRes stateText: Int,
        screenState: ScreenStateBinding,
        adapterToClean: LocationsAdapter
    ) {
        adapterToClean.injectData(emptyList())
        handleProgressIndicator(false)
        screenState.apply {
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

    private fun showSearchedLocations(
        locations: List<SearchLocationModel>,
        adapterToInject: LocationsAdapter,
        screenState: ScreenStateBinding
    ) {
        handleProgressIndicator(false)
        screenState.root.visibility = View.GONE
        adapterToInject.injectData(locations)
    }

    private fun handleProgressIndicator(isLoading: Boolean) {
        binding.circularProgress.apply {
            isIndeterminate = isLoading
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun onClickSearchedLocation(location: SearchLocationModel) {
        viewModel.savedConsultedLocation(location)
        findNavController().navigate(
            R.id.action_searchFragment_to_forecastsOfLocationFragment, bundleOf(
                "idLocation" to location.id
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}