package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    val day: DayDto,
    val astro: AstroDto,
    val hour: List<HourDto>
)
