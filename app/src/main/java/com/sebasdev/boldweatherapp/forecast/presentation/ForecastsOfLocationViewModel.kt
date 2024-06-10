package com.sebasdev.boldweatherapp.forecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.forecast.domain.use_cases.GetForecastsOfLocationUseCase
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ForecastsOfLocationViewModel @Inject constructor(
    private val getForecastsOfLocationUseCase: GetForecastsOfLocationUseCase
) : ViewModel() {

    protected val uiState: MutableLiveData<ForecastModel> = MutableLiveData()
    fun uiState(): LiveData<ForecastModel> = uiState

    fun forecastDetailsOfLocation() {
        viewModelScope.launch {
            when (val result = getForecastsOfLocationUseCase.invoke("Bogota", "2")) {
                is Resource.Error -> {
                    //uiState.value =
                }

                is Resource.Success -> {
                    uiState.value = result.data!!
                }
            }
        }
    }

}