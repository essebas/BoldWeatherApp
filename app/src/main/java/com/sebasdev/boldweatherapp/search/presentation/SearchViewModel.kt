package com.sebasdev.boldweatherapp.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel
import com.sebasdev.boldweatherapp.search.domain.use_cases.SearchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchLocationsUseCase: SearchLocationsUseCase
) : ViewModel() {

    protected val uiState: MutableLiveData<List<LocationModel>> = MutableLiveData()
    fun uiState(): LiveData<List<LocationModel>> = uiState

    fun searchLocation() {
        viewModelScope.launch {
            when (val result = searchLocationsUseCase.invoke("Colom")) {
                is Resource.Error -> {
                    uiState.value = emptyList()
                }

                is Resource.Success -> {
                    uiState.value = result.data!!
                }
            }
        }
    }
}