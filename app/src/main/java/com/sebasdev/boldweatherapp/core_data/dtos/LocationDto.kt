package com.sebasdev.boldweatherapp.core_data.dtos

import com.google.gson.annotations.SerializedName

data class LocationDto(
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val country: String,
    val id: Double?,
    val url: String?,
    @SerializedName("tz_id")
    val timeZoneName: String?,
    @SerializedName("localtime_epoch")
    val localDateTimeUnixTime: Int?,
    @SerializedName("localtime")
    val localTime: String?
)
