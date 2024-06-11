package com.sebasdev.boldweatherapp.search.domain.models

import com.sebasdev.boldweatherapp.core_domain.models.LocationModel

data class SearchLocationModel(
    val id: Int?,
    val url: String?,
    override val lat: Double,
    override val lon: Double,
    override val name: String,
    override val region: String,
    override val country: String,
) : LocationModel
