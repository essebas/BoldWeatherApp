package com.sebasdev.boldweatherapp.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import com.sebasdev.boldweatherapp.search.domain.use_cases.GetSearchLocationsSavedUseCase
import com.sebasdev.boldweatherapp.search.domain.use_cases.SaveConsultedLocationUseCase
import com.sebasdev.boldweatherapp.search.domain.use_cases.SearchLocationsUseCase
import com.sebasdev.boldweatherapp.search.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchLocationsUseCase: SearchLocationsUseCase,
    private val saveConsultedLocationUseCase: SaveConsultedLocationUseCase,
    private val getSearchLocationsSavedUseCase: GetSearchLocationsSavedUseCase
) : ViewModel() {

    private val _searchState: MutableStateFlow<SearchState<List<SearchLocationModel>>> =
        MutableStateFlow(SearchState.Idle)
    val searchState = _searchState.asStateFlow()

    private val _searchLocationsSaved: MutableStateFlow<SearchState<List<SearchLocationModel>>> =
        MutableStateFlow(SearchState.Idle)
    val searchLocationsSaved = _searchLocationsSaved.asStateFlow()

    init {
        getLocationsSaved()
    }

    fun searchLocation(query: String) {
        searchLocationsUseCase(query).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _searchState.value =
                        SearchState.Error(message = result.message ?: "An error occurred")
                }

                is Resource.Loading -> {
                    _searchState.value = SearchState.Loading
                }

                is Resource.Success -> {
                    _searchState.value = SearchState.Success(result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLocationsSaved() {
        getSearchLocationsSavedUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _searchLocationsSaved.value = SearchState.Success(result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun savedConsultedLocation(searchLocationModel: SearchLocationModel) {
        viewModelScope.launch {
            saveConsultedLocationUseCase(searchLocationModel)
        }
    }
}