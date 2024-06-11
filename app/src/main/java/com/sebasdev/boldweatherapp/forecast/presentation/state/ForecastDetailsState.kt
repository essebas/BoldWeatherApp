package com.sebasdev.boldweatherapp.forecast.presentation.state

sealed class ForecastDetailsState<out T> {
    data object Idle : ForecastDetailsState<Nothing>()
    data object Loading : ForecastDetailsState<Nothing>()
    data class Success<T>(val data: T) : ForecastDetailsState<T>()
    data class Error(val message: String) : ForecastDetailsState<Nothing>()
}