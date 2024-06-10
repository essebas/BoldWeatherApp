package com.sebasdev.boldweatherapp.search.domain.models

data class LocationModel(
    val id: Double,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val country: String,
    val url: String
)
