package com.sebasdev.boldweatherapp.forecast.domain.models

data class ForecastDayModel(
    val date: String,
    val conditionModel: ConditionModel,
    val maxTemperatureInCelsius: Double
)
