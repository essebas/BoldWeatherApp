package com.sebasdev.boldweatherapp.forecast.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdev.boldweatherapp.core_domain.util.ErrorCodes
import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastModel
import com.sebasdev.boldweatherapp.forecast.domain.use_cases.GetForecastsOfLocationUseCase
import com.sebasdev.boldweatherapp.forecast.presentation.state.ForecastDetailsState
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import com.sebasdev.boldweatherapp.search.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ForecastsOfLocationViewModel @Inject constructor(
    private val getForecastsOfLocationUseCase: GetForecastsOfLocationUseCase
) : ViewModel() {

    private val _forecastDetailsState: MutableStateFlow<ForecastDetailsState<ForecastModel>> =
        MutableStateFlow(ForecastDetailsState.Idle)
    val forecastDetailsState = _forecastDetailsState.asStateFlow()

    fun forecastDetailsOfLocation(
        idLocation: String,
        daysOfForecast: String,
        language: String? = null
    ) {

        getForecastsOfLocationUseCase(idLocation, daysOfForecast, language).onEach { result ->
            when (result) {
                is Resource.Error -> _forecastDetailsState.value = validateError(result.code, result.message)
                is Resource.Loading -> _forecastDetailsState.value = ForecastDetailsState.Loading
                is Resource.Success -> _forecastDetailsState.value = validateDataResult(result.data)
            }
        }.launchIn(viewModelScope)

    }

    private fun validateDataResult(result: ForecastModel?): ForecastDetailsState<ForecastModel> =
        if (result != null) {
            ForecastDetailsState.Success(result)
        } else {
            ForecastDetailsState.EmptyResult
        }

    private fun validateError(errorCode: Int?, message: String? = null) = when (errorCode) {
        ErrorCodes.EMPTY_RESULT -> ForecastDetailsState.EmptyResult
        ErrorCodes.NO_INTERNET_CONNECTION -> ForecastDetailsState.NoInternetConnection
        else -> ForecastDetailsState.GenericError
    }

}