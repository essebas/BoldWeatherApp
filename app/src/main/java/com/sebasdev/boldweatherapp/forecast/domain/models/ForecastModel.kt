package com.sebasdev.boldweatherapp.forecast.domain.models

data class ForecastModel(
    val location: ForecastLocationModel,
    val current: CurrentModel,
    val forecast: List<ForecastDayModel>
)
