package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class ForecastDayListDto(
    @SerializedName("forecastday")
    val listOfForecastDays: List<ForecastDayDto>
)
