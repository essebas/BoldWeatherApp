package com.sebasdev.boldweatherapp.core_domain.models

interface LocationModel {
    val lat: Double
    val lon: Double
    val name: String
    val region: String
    val country: String
}
