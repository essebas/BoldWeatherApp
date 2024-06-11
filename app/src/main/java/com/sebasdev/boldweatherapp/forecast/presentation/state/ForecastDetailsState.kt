package com.sebasdev.boldweatherapp.forecast.presentation.state

import com.sebasdev.boldweatherapp.search.presentation.state.SearchState

sealed class ForecastDetailsState<out T> {
    data object Idle : ForecastDetailsState<Nothing>()
    data object Loading : ForecastDetailsState<Nothing>()
    data object EmptyResult : ForecastDetailsState<Nothing>()
    data object NoInternetConnection : ForecastDetailsState<Nothing>()
    data object GenericError : ForecastDetailsState<Nothing>()
    data class Success<T>(val data: T) : ForecastDetailsState<T>()
    data class Error(val message: String) : ForecastDetailsState<Nothing>()
}