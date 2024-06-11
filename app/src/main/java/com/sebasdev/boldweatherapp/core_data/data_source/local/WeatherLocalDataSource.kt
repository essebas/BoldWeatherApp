package com.sebasdev.boldweatherapp.core_data.data_source.local

import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

interface WeatherLocalDataSource {

    suspend fun insertSearchedLocation(idLocation: Int, name: String, country: String)

    suspend fun getSearchLocationsSaved(): List<SearchLocationModel>

}