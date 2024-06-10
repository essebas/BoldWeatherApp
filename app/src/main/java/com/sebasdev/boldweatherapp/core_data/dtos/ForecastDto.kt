package com.sebasdev.boldweatherapp.core_data.dtos

data class ForecastDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDayListDto
)
