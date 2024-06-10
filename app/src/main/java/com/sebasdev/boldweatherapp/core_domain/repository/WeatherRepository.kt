package com.sebasdev.boldweatherapp.core_domain.repository

import com.sebasdev.boldweatherapp.core_domain.util.Resource
import com.sebasdev.boldweatherapp.search.domain.models.LocationModel

interface WeatherRepository {

    suspend fun getSearchResultsByQuery(query: String): Resource<List<LocationModel>>

}