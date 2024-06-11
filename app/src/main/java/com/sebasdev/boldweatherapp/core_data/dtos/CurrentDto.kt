package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Int,
    val condition: ConditionDto,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("heatindex_c")
    val heatIndexC: Double,
)
