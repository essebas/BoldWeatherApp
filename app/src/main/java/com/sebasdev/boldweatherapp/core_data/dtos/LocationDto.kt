package com.sebasdev.boldweatherapp.core_data.dtos

data class LocationDto(
    val id: Double,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val country: String,
    val url: String
)
