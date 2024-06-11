package com.sebasdev.boldweatherapp.forecast.domain.models

data class CurrentModel(
    val lastUpdated: String,
    val tempInCelsius: Double,
    val isDay: Int,
    val condition: ConditionModel,
    val windSpeedInKilometers: Double,
    val humidity: Int,
    val heatIndexInCelsius: Double
)
