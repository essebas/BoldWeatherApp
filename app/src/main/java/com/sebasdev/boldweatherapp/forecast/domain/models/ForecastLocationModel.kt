package com.sebasdev.boldweatherapp.forecast.domain.models

import com.sebasdev.boldweatherapp.core_domain.models.LocationModel

data class ForecastLocationModel(
    val timeZoneName: String?,
    val localDateTimeUnixTime: Int?,
    val localTime: String?,
    override val lat: Double,
    override val lon: Double,
    override val name: String,
    override val region: String,
    override val country: String
) : LocationModel
